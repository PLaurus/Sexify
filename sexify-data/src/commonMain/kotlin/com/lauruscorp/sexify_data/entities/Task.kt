package com.lauruscorp.sexify_data.entities

data class Task(
    val id: Long,
    val text: Text,
    val textTranslations: List<Text>,
    val stage: Stage,
    val doerSexes: List<SexifySex>,
    val partnerSexes: List<SexifySex>,
    val timerSec: Int? = null
) {
    data class Stage(
        val id: Long,
        val name: Text,
        val nameTranslations: List<Text>,
        val description: Text? = null,
        val descriptionTranslations: List<Text> = emptyList()
    ) {
        fun getTranslatedName(
            language: SexifyLanguage
        ): Text? {
            return nameTranslations.firstOrNull { translation ->
                translation.language == language
            }
        }

        fun getTranslatedDescription(
            language: SexifyLanguage
        ): Text? {
            return descriptionTranslations.firstOrNull { translation ->
                translation.language == language
            }
        }
    }

    fun getTranslatedText(
        language: SexifyLanguage
    ): Text? {
        return textTranslations.firstOrNull { translation ->
            translation.language == language
        }
    }
}