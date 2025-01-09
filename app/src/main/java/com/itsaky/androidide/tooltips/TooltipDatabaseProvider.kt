package com.itsaky.androidide.tooltips

import android.content.Context
import androidx.room.Room
import com.itsaky.androidide.tooltip.TooltipDatabase

object TooltipDatabaseProvider {
    private var INSTANCE: TooltipDatabase? = null

    fun getDatabase(context: Context): TooltipDatabase {
        if (INSTANCE == null) {
            synchronized(TooltipDatabase::class) {
                INSTANCE = Room.databaseBuilder(
                    context.applicationContext,
                    TooltipDatabase::class.java,
                    "tooltip_database"
                ).build()
            }
        }
        return INSTANCE!!
    }
}