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

package com.itsaky.androidide.templates.base

import com.itsaky.androidide.templates.base.models.Dependency

/**
 * Configures the template to use AndroidX and Material Design Components dependencies.
 */
fun AndroidModuleTemplateBuilder.baseAndroidXDependencies() {
  addDependency(Dependency.AndroidX.AppCompat)
  addDependency(Dependency.AndroidX.ConstraintLayout)
  addDependency(Dependency.Google.Material)
  addDependency(Dependency.AndroidX.Startup_Runtime)
  addDependency(Dependency.AndroidX.Interpolator)
}

fun AndroidModuleTemplateBuilder.composeDependencies() {
  addDependency(Dependency.Compose.BOM, isPlatform = true)
  addDependency(Dependency.Compose.Material3)
  addDependency(Dependency.Compose.UI_Tooling)
  addDependency(Dependency.Compose.LifeCycle_Runtime_Ktx)
  addDependency(Dependency.Compose.Activity)
  addDependency(Dependency.Compose.UI_Graphics)
  addDependency(Dependency.Compose.UI)
  addDependency(Dependency.Compose.UI_Tooling_Preview)
  addDependency(Dependency.Compose.Core_Ktx)
  addDependency(Dependency.Compose.UI_Test_Manifest)
  addDependency(Dependency.Compose.Collection_Ktx)

  addCustomDependency(Dependency.Compose.Kotlin_Stdlib)
}