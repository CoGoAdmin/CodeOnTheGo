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

package com.itsaky.androidide.tooltips.java

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.itsaky.androidide.tooltips.ButtonsConverters
import com.itsaky.androidide.tooltips.TooltipItem
import com.itsaky.androidide.tooltips.java.JavaTooltipDao

// Annotates class to be a Room Database with a table (entity) of the Message class
@Database(entities = [TooltipItem::class], version = 2, exportSchema = false)
@TypeConverters(ButtonsConverters::class)
abstract class JavaTooltipDatabase : RoomDatabase() {
    abstract fun javatooltipDao(): JavaTooltipDao


    companion object {
        @Volatile
        private var instance: JavaTooltipDatabase? = null

        fun getDatabase(context: Context): JavaTooltipDatabase {
            return instance ?: synchronized(this) {
                val newInstance = Room.databaseBuilder(
                    context.applicationContext,
                    JavaTooltipDatabase::class.java,
                    "java_tooltip_database"
                ).build()
                instance = newInstance
                newInstance
            }
        }
    }
}


