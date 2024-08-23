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

package com.itsaky.androidide.activities.editor

import android.view.View
import com.itsaky.androidide.app.EdgeToEdgeIDEActivity
import com.itsaky.androidide.databinding.ActivityFaqBinding

class FAQActivity : EdgeToEdgeIDEActivity() {

    private var _binding: ActivityFaqBinding? = null
    private val binding: ActivityFaqBinding
        get() = checkNotNull(_binding) {
            "FAQActivity has been destroyed"
        }

    override fun bindLayout(): View {
        _binding = ActivityFaqBinding.inflate(layoutInflater)
        return binding.root
    }
}