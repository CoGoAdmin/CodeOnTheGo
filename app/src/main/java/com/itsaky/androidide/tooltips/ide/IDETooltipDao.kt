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

package com.itsaky.androidide.tooltips.ide

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.itsaky.androidide.tooltips.TooltipItem

@Dao
interface IDETooltipDao {
  @Query("SELECT * FROM TooltipItem ORDER BY tooltipTag ASC")
  fun getIDETooltipItems(): List<TooltipItem>

  @Query("SELECT tooltipSummary FROM TooltipItem WHERE tooltipTag == :tooltipTag")
  fun getSummary(tooltipTag : String) : String

  @Query("SELECT tooltipDetail FROM TooltipItem WHERE tooltipTag == :tooltipTag")
  fun getDetail(tooltipTag : String) : String

  //@Query("SELECT tooltipButtons FROM ide_tooltip_table WHERE tooltipTag == :tooltipTag")
  //fun getButtons(tooltipTag: String) : ArrayList<Pair<String, String>>

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  suspend fun insert(tooltipItem: TooltipItem)

  @Query("SELECT * FROM TooltipItem WHERE tooltipTag == :tooltipTag")
  suspend fun getTooltip(tooltipTag: String) : TooltipItem

  @Query("DELETE FROM TooltipItem")
  suspend fun deleteAll()

  @Query("SELECT COUNT(*) FROM TooltipItem")
  suspend fun getCount(): Int
}