package com.itsaky.androidide.tooltip

import androidx.room.AutoMigration
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.itsaky.androidide.tooltips.ButtonsConverters
import com.itsaky.androidide.tooltips.TooltipItem
import com.itsaky.androidide.tooltips.ide.IDETooltipDao
import com.itsaky.androidide.tooltips.java.JavaTooltipDao
import com.itsaky.androidide.tooltips.kotlin.KotlinTooltipDao


@Database(
    entities = [TooltipItem::class],
    version = 1
)
@TypeConverters(ButtonsConverters::class)
abstract class TooltipDatabase : RoomDatabase() {
    abstract fun ideTooltipDao() : IDETooltipDao
    abstract fun javaTooltipDao() : JavaTooltipDao
    abstract fun kotlinTooltipDao() : KotlinTooltipDao
}

