package com.lauruscorp.sexify_data.entities.errors

import com.lauruscorp.sexify_data.entities.SexifyLanguage
import com.lauruscorp.sexify_data.entities.SexifySex

sealed interface TasksDataError {
    object UnknownError : TasksDataError
    data class NotExists(val taskId: Long) : TasksDataError

    sealed interface OriginalTextError : TasksDataError {
        data class IncorrectDataForDb(
            val text: String,
            val language: SexifyLanguage
        ) : OriginalTextError

        data class DBLanguageDoesNotExist(
            val language: SexifyLanguage
        ) : OriginalTextError
    }

    sealed interface TranslationError : TasksDataError {
        val language: SexifyLanguage

        data class DbLanguageDoesNotExist(
            override val language: SexifyLanguage
        ) : TranslationError
    }

    sealed interface TaskStageError : TasksDataError {
        data class DbStageDoesNotExist(val stageId: Long) : TaskStageError
    }

    sealed interface DoerSexesError : TasksDataError {
        data class DbSexDoesNotExist(val sex: SexifySex) : DoerSexesError
    }

    sealed interface PartnerSexesError : TasksDataError {
        data class DbSexDoesNotExist(val sex: SexifySex) : PartnerSexesError
    }
}
