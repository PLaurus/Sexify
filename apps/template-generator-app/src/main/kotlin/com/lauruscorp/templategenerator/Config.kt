package com.lauruscorp.templategenerator

import java.nio.file.Path
import kotlin.io.path.Path

object Config {
	val COMPANY_PACKAGE: Path = Path("com", "lauruscorp")
	
	val SETTINGS_GRADLE_PATH = Path("")
	
	val DOMAIN_TEMPLATE_PATH = Path("templates", "domain")
	val FEATURE_TEMPLATE_PATH = Path("templates", "feature-with-domain")
	
	val RESULT_ROOT_PATH: Path = Path("features")
	
	const val DOMAIN_NAME_SUFFIX = "domain"
	const val FEATURE_NAME_SUFFIX = "feature"
}