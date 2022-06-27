package _M_PACKAGE_.store

import com.arkivanov.mvikotlin.core.store.Store

interface _M_NAME_PASCAL_Store : Store<_M_NAME_PASCAL_Store.Intent, _M_NAME_PASCAL_Store.State, _M_NAME_PASCAL_Store.Label> {
	sealed interface Intent
	
	sealed interface Action
	
	data class State(
		val text: String
	)
	
	sealed interface Message
	
	sealed interface Label {
		data class ThrowableError(val throwable: Throwable) : Label
	}
}