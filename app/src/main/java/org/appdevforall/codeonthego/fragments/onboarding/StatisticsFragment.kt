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

package org.appdevforall.codeonthego.fragments.onboarding

import android.content.Context
import android.os.Bundle
import android.view.ViewGroup
import org.appdevforall.codeonthego.R
import org.appdevforall.codeonthego.app.IDEApplication
import org.appdevforall.codeonthego.databinding.LayoutOnboardingStatisticsBinding

class StatisticsFragment : OnboardingFragment() {

  private var _content: LayoutOnboardingStatisticsBinding? = null
  private val content: LayoutOnboardingStatisticsBinding
    get() = checkNotNull(_content) { "Fragment has been destroyed" }

  companion object {

    @JvmStatic
    fun newInstance(context: Context): StatisticsFragment {
      return StatisticsFragment().apply {
        arguments = Bundle().apply {
          putCharSequence(KEY_ONBOARDING_TITLE,
            context.getString(R.string.title_androidide_statistics))
          putCharSequence(KEY_ONBOARDING_SUBTITLE,
            context.getString(R.string.idepref_stats_optIn_summary))
        }
      }
    }
  }

  override fun createContentView(parent: ViewGroup, attachToParent: Boolean) {
    _content = LayoutOnboardingStatisticsBinding.inflate(layoutInflater, parent, attachToParent)
    content.statOptIn.isChecked = true
  }

  override fun onDestroy() {
    super.onDestroy()
    _content = null
  }

  fun updateStatOptInStatus() {
    com.itsaky.androidide.preferences.internal.StatPreferences.statOptIn =
      _content?.statOptIn?.isChecked ?: false

    IDEApplication.instance.reportStatsIfNecessary()
  }
}