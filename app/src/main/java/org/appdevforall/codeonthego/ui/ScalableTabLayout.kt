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

package org.appdevforall.codeonthego.ui

import android.content.Context
import android.util.AttributeSet
import android.view.ViewGroup
import com.google.android.material.tabs.TabLayout

// https://stackoverflow.com/a/65605542
class ScalableTabLayout : TabLayout {
  constructor(context: Context) : super(context)
  constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)
  constructor(
    context: Context,
    attrs: AttributeSet?,
    defStyleAttr: Int
  ) : super(context, attrs, defStyleAttr)

  override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
    val tabLayout = getChildAt(0) as ViewGroup
    val childCount = tabLayout.childCount
    if (childCount > 0) {
      val widthPixels = MeasureSpec.getSize(widthMeasureSpec)
      val tabMinWidth = widthPixels / childCount
      var remainderPixels = widthPixels % childCount
      for (i in 0 until childCount) {
        val it = tabLayout.getChildAt(i)
        if (remainderPixels > 0) {
          it.minimumWidth = tabMinWidth + 1
          remainderPixels--
        } else {
          it.minimumWidth = tabMinWidth
        }
      }
    }
    super.onMeasure(widthMeasureSpec, heightMeasureSpec)
  }
}
