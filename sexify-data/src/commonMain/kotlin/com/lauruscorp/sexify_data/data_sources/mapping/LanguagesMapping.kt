package com.lauruscorp.sexify_data.data_sources.mapping

import com.lauruscorp.sexify_data.databases.tasks.aliases.DbLanguage
import com.lauruscorp.sexify_data.entities.SexifyLanguage

// TODO: make internal
fun DbLanguage.toSexifyLanguage(): SexifyLanguage? {
	return when (id) {
		SexifyLanguage.English.tag -> SexifyLanguage.English
		SexifyLanguage.Russian.tag -> SexifyLanguage.Russian
		SexifyLanguage.French.tag -> SexifyLanguage.French
		SexifyLanguage.German.tag -> SexifyLanguage.German
		SexifyLanguage.Portuguese.tag -> SexifyLanguage.Portuguese
		SexifyLanguage.Korean.tag -> SexifyLanguage.Korean
		SexifyLanguage.Italian.tag -> SexifyLanguage.Italian
		SexifyLanguage.Japanese.tag -> SexifyLanguage.Japanese
		SexifyLanguage.Dutch.tag -> SexifyLanguage.Dutch
		SexifyLanguage.Chinese.tag -> SexifyLanguage.Chinese
		else -> null
	}
}