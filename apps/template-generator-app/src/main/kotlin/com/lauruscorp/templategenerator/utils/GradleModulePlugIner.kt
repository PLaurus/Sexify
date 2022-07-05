package com.lauruscorp.templategenerator.utils

import java.nio.file.Path
import kotlin.io.path.appendLines
import kotlin.io.path.isDirectory

internal class GradleModulePlugIner {
	fun plugIntoProject(
		modulePath: Path,
		settingGradlePath: Path
	) {
		val gradlePath = modulePath.toGradleModuleString()
		val existingSettingsGradlePath = settingGradlePath.toExistingPath()
		val newSettingGradlePath = if (existingSettingsGradlePath.isDirectory()) {
			PathBuilder(existingSettingsGradlePath)
				.append(SETTINGS_GRADLE_FILE_NAME)
				.build()
		} else {
			existingSettingsGradlePath
		}
		
		val plugInString = "include '$gradlePath'"
		newSettingGradlePath.appendLines(listOf(plugInString))
	}
	
	companion object {
		private const val SETTINGS_GRADLE_FILE_NAME = "settings.gradle"
	}
}