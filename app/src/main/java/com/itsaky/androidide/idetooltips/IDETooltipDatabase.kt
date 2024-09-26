/*
 *  This file is part of AndroidIDE.
 *
 *  AndroidIDE is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  AndroidIDE is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *   along with AndroidIDE.  If not, see <https://www.gnu.org/licenses/>.
 */

package com.itsaky.androidide.idetooltips

import android.content.Context
import android.util.Log
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.BufferedReader
import java.io.InputStreamReader

// Annotates class to be a Room Database with a table (entity) of the Message class
@Database(entities = [IDETooltipItem::class], version = 2, exportSchema = false)
@TypeConverters(ButtonsConverters::class)
abstract class IDETooltipDatabase : RoomDatabase() {
    abstract fun idetooltipDao(): IDETooltipDao


    companion object {
        @Volatile
        private var instance: IDETooltipDatabase? = null

        fun getDatabase(context: Context): IDETooltipDatabase {
            return instance ?: synchronized(this) {
                val newInstance = Room.databaseBuilder(
                    context.applicationContext,
                    IDETooltipDatabase::class.java,
                    "ide_tooltip_database"
                ).build()
                newInstance.loadData(context)
                instance = newInstance
                newInstance
            }
        }
    }

    fun loadData(context: Context) {
        CoroutineScope(Dispatchers.IO).launch {
            populateDatabaseFromCSV(context)
        }
    }

    private suspend fun populateDatabaseFromCSV(context: Context) {
        // Reading the CSV file from assets
        val db = getDatabase(context)
        val dao = db.idetooltipDao()

        val inputStream = context.assets.open("idetooltips/idetooltips.csv")
        val reader = BufferedReader(InputStreamReader(inputStream))
        var line: String?
        val buttons = ArrayList<Pair<String, String>>()

        try {
            // Loop through the lines of the CSV
            while (reader.readLine().also { line = it } != null) {
                // Split the line by comma
                val parts = line!!.split("|")
                if (parts.size > 4) {
                    val id = parts[0]
                    val scope = parts[1]
                    val summary = parts[2]
                    val detail = parts[3]

                    val nButtons:Int = parts[4].toInt()
                    val start:Int = 5
                    val stop:Int = start + nButtons * 2
                    for (index in start..stop step 2) {
                        val buttonText = parts[index]
                        val buttonURI = parts[index+1]
                        buttons.add(Pair(buttonText, buttonURI))
                    }

                    val item = IDETooltipItem(tooltipTag = id, summary = summary, detail = detail, buttons = buttons)

                    // Insert into the database
                    dao.insert(item)
                }
            }
        } catch (exception: Exception) {
            Log.e(
                "User App",
                exception.localizedMessage ?: "failed to pre-populate users into database"
            )
        } finally {
            withContext(Dispatchers.IO) {
                reader.close()
            } // Ensure the reader is closed after use
        }

        val IDETooltipItemList: List<IDETooltipItem> = dao.getTooltipItems()
        IDETooltipItemList.forEach { tooltipItem ->
            Log.d(
                "TooltipRoomDatabase",
                "after insert database - itemTag = ${tooltipItem.tooltipTag}, " +
                        "summary = ${tooltipItem.summary}, detail=${tooltipItem.detail}"
            )
        }
    }
}

//        companion object {
//            // Singleton prevents multiple instances of database opening at the
//            // same time.
//            @Volatile
//            private var INSTANCE: TooltipRoomDatabase? = null
//
//            fun getDatabase(context: Context, scope: CoroutineScope): IDETooltipRoomDatabase {
//                // if the INSTANCE is not null, then return it,
//                // if it is, then create the database
//                return INSTANCE ?: synchronized(IDETooltipRoomDatabase::class) {
//                    val instance = Room.databaseBuilder(
//                        context.applicationContext,
//                        IDETooltipRoomDatabase::class.java,
//                        "ide_tooltip_database"
//                    ).build()
//                    instance
//                }
//            }
//        }
//    }
