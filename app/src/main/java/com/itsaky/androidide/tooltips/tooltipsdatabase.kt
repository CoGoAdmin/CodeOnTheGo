package com.itsaky.androidide.tooltip

import androidx.room.Database
import androidx.room.RoomDatabase
import com.itsaky.androidide.tooltips.ide.IDETooltipDao
import com.itsaky.androidide.tooltips.ide.IDETooltipItem
import com.itsaky.androidide.tooltips.java.JavaTooltipDao
import com.itsaky.androidide.tooltips.java.JavaTooltipItem
import com.itsaky.androidide.tooltips.kotlin.KotlinTooltipDao
import com.itsaky.androidide.tooltips.kotlin.KotlinTooltipItem


@Database(
    entities = [IDETooltipItem::class, JavaTooltipItem::class, KotlinTooltipItem::class],
    version = 1
)

abstract class TooltipDatabase : RoomDatabase() {
    abstract fun ideTooltipDao() : IDETooltipDao
    abstract fun javaTooltipDao() : JavaTooltipDao
    abstract fun kotlinTooltipDao() : KotlinTooltipDao
}