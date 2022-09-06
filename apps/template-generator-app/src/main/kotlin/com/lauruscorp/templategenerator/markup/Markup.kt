package com.lauruscorp.templategenerator.markup

sealed interface Markup {
    object Package : MultiMark(listOf("_M_PACKAGE_0_", "m_package_1"))
    object NameOriginal : Mark("_M_NAME_ORIGINAL_")
    object NamePascal : MultiMark(listOf("_M_NAME_PASCAL_", "MNameSnake"))
    object GradlePlugInModule : MultiMark(listOf("_M_GRADLE_PLUG_IN_MODULE_0_"))
    object NameLowercase : Mark("_M_NAME_LOWERCASE_")
    object NameCamel : Mark("_M_NAME_CAMEL_")

    // This mark must be in snake case! (it is used in layout naming).
    object NameSnake : Mark("_m_name_snake_")
    object RemoveLine : Mark("_M_REMOVE_LINE_")
}

sealed class Mark(val mark: String) : Markup
sealed class MultiMark(val marks: List<String>) : Markup