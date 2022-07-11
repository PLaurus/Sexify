package _M_PACKAGE_0_.store

import com.arkivanov.mvikotlin.core.store.Store

interface _M_NAME_PASCAL_Store : Store<_M_NAME_PASCAL_Store.Intent, _M_NAME_PASCAL_Store.State, _M_NAME_PASCAL_Store.Label> {
	sealed interface Intent
	
	sealed interface Action
	
	data class State(
		val someValue: Any?
	)
	
	sealed interface Message
	
	sealed interface Label {
		data class ExceptionError(val exception: Exception) : Label
	}
}