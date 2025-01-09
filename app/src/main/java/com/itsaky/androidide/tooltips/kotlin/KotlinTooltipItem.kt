package com.itsaky.androidide.tooltips.kotlin

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "kotlin_tooltip_table")
data class KotlinTooltipItem(
    @PrimaryKey @ColumnInfo(name = "tooltipTag") val tooltipTag : String,
    @ColumnInfo(name = "tooltipSummary") val summary: String,
    @ColumnInfo(name = "tooltipDetail")  val detail: String,
    @ColumnInfo(name = "tooltipButtons") val buttons: ArrayList<Pair<String, String>>
)
