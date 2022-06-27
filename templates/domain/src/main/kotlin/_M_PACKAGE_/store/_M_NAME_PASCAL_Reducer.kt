package _M_PACKAGE_.store

import com.arkivanov.mvikotlin.core.store.Reducer
import javax.inject.Inject

internal class _M_NAME_PASCAL_Reducer @Inject constructor(
) : Reducer<_M_NAME_PASCAL_Store.State, _M_NAME_PASCAL_Store.Message> {
	override fun _M_NAME_PASCAL_Store.State.reduce(msg: _M_NAME_PASCAL_Store.Message): _M_NAME_PASCAL_Store.State {
		return when (msg) {
			else -> copy(text = "")
		}
	}
}