package com.lauruscorp.sexify_data.entities

/**
 * Languages supported by sexify applications.
 *
 * https://www.iana.org/assignments/language-subtag-registry/language-subtag-registry
 */
//
enum class SexifyLanguage(
	val tag: String
) {
	English(tag = "en"),
	Russian(tag = "ru"),
	French(tag = "fr"),
	German(tag = "de"),
	Portuguese(tag = "pt"),
	Korean(tag = "ko"),
	Italian(tag = "it"),
	Japanese(tag = "ja"),
	Dutch(tag = "nl"),
	Chinese(tag = "zh"),
}