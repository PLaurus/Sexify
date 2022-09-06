package _M_PACKAGE_0_.store

import com.arkivanov.mvikotlin.extensions.coroutines.CoroutineExecutor
import com.lauruscorp.core_jvm.coroutines.CoroutineDispatchers
import javax.inject.Inject

internal class _M_NAME_PASCAL_Executor @Inject constructor(
    coroutineDispatchers: CoroutineDispatchers
) : CoroutineExecutor<_M_NAME_PASCAL_Store.Intent, _M_NAME_PASCAL_Store.Action, _M_NAME_PASCAL_Store.State, _M_NAME_PASCAL_Store.Message, _M_NAME_PASCAL_Store.Label>(
    mainContext = coroutineDispatchers.main
) {
    override fun executeAction(
        action: _M_NAME_PASCAL_Store.Action,
        getState: () -> _M_NAME_PASCAL_Store.State
    ) {
        when (action) {
            else -> {}
        }
    }

    override fun executeIntent(
        intent: _M_NAME_PASCAL_Store.Intent,
        getState: () -> _M_NAME_PASCAL_Store.State
    ) {
        when (intent) {
            else -> {}
        }
    }
}