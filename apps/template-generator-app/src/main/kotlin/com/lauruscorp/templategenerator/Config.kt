package com.lauruscorp.templategenerator

object Config {
	const val TEMPLATES_PATH = "templates"
	const val SETTINGS_GRADLE_PATH = ""
	
	/**
	 * It is relative to [TEMPLATES_PATH]
	 */
	const val DOMAIN_TEMPLATE_PATH = "domain"
	
	/**
	 * It is relative to [TEMPLATES_PATH]
	 */
	const val FEATURE_TEMPLATE_PATH = "feature"
	
	const val COMPANY_PACKAGE = "com.lauruscorp"
	
	/**
	 * This path will be used as root for new modules.
	 */
	const val RESULT_ROOT_PATH = "features"
}