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

package com.itsaky.androidide.inflater.internal.adapters

import android.widget.VideoView
import com.itsaky.androidide.annotations.uidesigner.IncludeInDesigner
import com.itsaky.androidide.annotations.uidesigner.IncludeInDesigner.Group.WIDGETS
import com.itsaky.androidide.inflater.models.UiWidget
import org.appdevforall.codeonthego.resources.R.drawable
import org.appdevforall.codeonthego.resources.R.string

/**
 * Attribute adapter for [VideoView].
 *
 * @author Deep Kr. Ghosh
 */
@com.itsaky.androidide.annotations.inflater.ViewAdapter(VideoView::class)
@IncludeInDesigner(group = WIDGETS)
open class VideoViewAdapter<T : VideoView> : SurfaceViewAdapter<T>() {
  override fun createUiWidgets(): List<UiWidget> {
    return listOf(
      UiWidget(VideoView::class.java, string.widget_videoview, drawable.ic_widget_videoview)
    )
  }
}
