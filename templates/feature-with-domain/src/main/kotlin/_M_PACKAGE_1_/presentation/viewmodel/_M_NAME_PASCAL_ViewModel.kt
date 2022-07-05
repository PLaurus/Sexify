package _M_PACKAGE_1_.presentation.viewmodel

import _M_PACKAGE_1_.presentation.entities.UiError
import androidx.lifecycle.LiveData

internal interface _M_NAME_PASCAL_ViewModel {
	val errorEvent: LiveData<UiError>
}