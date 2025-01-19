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

import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import com.itsaky.androidide.build.config.BuildConfig

plugins {
  kotlin("jvm")
}

group = "${BuildConfig.packageName}.annotations"

dependencies {
  implementation(kotlin("stdlib"))
  
  implementation(projects.annotations)
  
  implementation(libs.androidx.annotation)
  implementation(libs.common.javapoet)
  implementation(libs.common.ksp)
}

sourceSets.main {
  java.srcDirs("src/main/kotlin")
}

tasks.withType<KotlinCompile> {
  kotlinOptions.jvmTarget = "17"
}

kotlin {
  jvmToolchain(17)
}

java {
  sourceCompatibility = JavaVersion.VERSION_17
  targetCompatibility = JavaVersion.VERSION_17
}