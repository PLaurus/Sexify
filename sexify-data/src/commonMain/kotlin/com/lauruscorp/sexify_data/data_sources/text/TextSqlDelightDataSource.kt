package com.lauruscorp.sexify_data.data_sources.text

import com.lauruscorp.sexify_data.data_sources.mapping.toSexifyLanguage
import com.lauruscorp.sexify_data.databases.tasks.TasksDatabase
import com.lauruscorp.sexify_data.databases.tasks.utils.getOriginalLanguage
import com.lauruscorp.sexify_data.databases.tasks.utils.getOriginalText
import com.lauruscorp.sexify_data.databases.tasks.utils.selectTextContentById
import com.lauruscorp.sexify_data.entities.Text

// TODO: implement this method as real data source or in another way
internal fun TasksDatabase.getText(
    textId: Long
): Text? {
    return Text(
        language = selectTextContentById(textId)
            ?.getOriginalLanguage(this)
            ?.toSexifyLanguage()
            ?: return null,
        value = getOriginalText(textId) ?: ""
    )
}