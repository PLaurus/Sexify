package com.lauruscorp.features.couplenameseditordomain.store

import com.arkivanov.mvikotlin.core.store.Store
import com.lauruscorp.features.couplenameseditordomain.entities.Sex

interface CoupleNamesEditorStore : Store<CoupleNamesEditorStore.Intent, CoupleNamesEditorStore.State, CoupleNamesEditorStore.Label> {
	sealed interface Intent {
		data class UpdateFirstPlayerName(val name: String?) : Intent
		data class UpdateFirstPlayerSex(val sex: Sex?) : Intent
		data class UpdateSecondPlayerName(val name: String?) : Intent
		data class UpdateSecondPlayerSex(val sex: Sex?) : Intent
	}

	sealed interface Action {
		object LoadData : Action
	}

	data class State(
		val isLoading: Boolean,
		val firstPlayerName: String?,
		val firstPlayerSex: Sex?,
		val secondPlayerName: String?,
		val secondPlayerSex: Sex?,
		val isAllNecessaryDataProvided: Boolean
	)

	sealed interface Message {
		object StartedLoadingData : Message

		data class FinishedLoadingData(
			val firstPlayerName: String?,
			val firstPlayerSex: Sex?,
			val secondPlayerName: String?,
			val secondPlayerSex: Sex?,
		) : Message

		data class UpdateFirstPlayerName(val name: String?) : Message
		data class UpdateFirstPlayerSex(val sex: Sex?) : Message
		data class UpdateSecondPlayerName(val name: String) : Message
		data class UpdateSecondPlayerSex(val sex: Sex?) : Message
		data class CheckedIsAllNecessaryDataProvided(val result: Boolean) : Message
	}

	sealed interface Label {
		data class ExceptionError(val exception: Exception) : Label
	}
}