package com.itsaky.androidide.tooltips

import android.content.Context
import androidx.annotation.WorkerThread
import androidx.room.Room
import com.itsaky.androidide.tooltip.TooltipDatabase

object TooltipDatabaseProvider {
    private var INSTANCE: TooltipDatabase? = null

    @JvmStatic
    @WorkerThread
    fun init() {

    }

    fun getDatabase(context: Context): TooltipDatabase {
        if (INSTANCE == null) {
            synchronized(TooltipDatabase::class) {
                INSTANCE = Room.databaseBuilder(
                    context.applicationContext,
                    TooltipDatabase::class.java,
                    "tooltip_database"
                ).build()
                LoadTooltips.loadData(context)
            }
        }
        return INSTANCE!!
    }
}