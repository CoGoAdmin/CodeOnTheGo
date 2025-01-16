package com.itsaky.androidide.tooltips

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

    @Entity
    data class TooltipItem(
        @PrimaryKey @ColumnInfo(name = "tooltipTag") val tooltipTag : String,
        @ColumnInfo(name = "tooltipSummary") val summary: String,
        @ColumnInfo(name = "tooltipDetail")  val detail: String,
        @ColumnInfo(name = "tooltipButtons") val buttons: ArrayList<Pair<String, String>>
    )

