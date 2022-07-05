package _M_PACKAGE_1_.presentation.viewmodel

import _M_PACKAGE_0_.store._M_NAME_PASCAL_Store
import _M_PACKAGE_1_.presentation.entities.UiError
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.arkivanov.mvikotlin.extensions.coroutines.labels
import com.lauruscorp.core.android.livedata.EventLiveData
import com.lauruscorp.core.mapping.Mapper
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

internal class _M_NAME_PASCAL_ViewModelImpl @Inject constructor(
	private val store: _M_NAME_PASCAL_Store,
	private val labelToUiErrorMapper: @JvmSuppressWildcards Mapper<_M_NAME_PASCAL_Store.Label, UiError>
) : ViewModel(), _M_NAME_PASCAL_ViewModel {
//	override val a: LiveData<String> = MutableLiveData<String>().apply {
//		viewModelScope.launch {
//			store.states
//				.map { state -> state.a?.toString() ?: "" }
//				.distinctUntilChanged()
//				.collect(::setValue)
//		}
//	}
	
	override val errorEvent: LiveData<UiError> = EventLiveData<UiError>().apply {
		viewModelScope.launch {
			store.labels
				.map(labelToUiErrorMapper::map)
				.collect(::setValue)
		}
	}

//	override fun updateA(a: String) {
//		store.accept(
//			ExampleStore.Intent.UpdateA(
//				a = a.toIntOrNull()
//			)
//		)
//	}
}