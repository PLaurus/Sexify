package _M_PACKAGE_1_.presentation.di.modules.viewmodel

import _M_PACKAGE_1_.presentation.viewmodel._M_NAME_PASCAL_ViewModel
import _M_PACKAGE_1_.presentation.viewmodel._M_NAME_PASCAL_ViewModelImpl
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import com.lauruscorp.core.android.viewmodel.DaggerViewModelFactory
import com.lauruscorp.core.di.dagger.mapkeys.ViewModelMapKey
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap
import javax.inject.Provider

@Module
internal abstract class ViewModelModule {
	@Binds
	@IntoMap
	@ViewModelMapKey(_M_NAME_PASCAL_ViewModelImpl::class)
	abstract fun bind_M_NAME_PASCAL_ViewModelIntoMap(
		_M_NAME_CAMEL_ViewModel: _M_NAME_PASCAL_ViewModelImpl
	): ViewModel
	
	companion object {
		@Provides
		fun provideViewModelFactory(
			viewModelProviders: Map<Class<out ViewModel>, @JvmSuppressWildcards Provider<ViewModel>>
		): ViewModelProvider.Factory {
			return DaggerViewModelFactory(viewModelProviders)
		}
		
		@Provides
		fun provide_M_NAME_PASCAL_ViewModel(
			viewModelStoreOwner: ViewModelStoreOwner,
			viewModelFactory: ViewModelProvider.Factory
		): _M_NAME_PASCAL_ViewModel {
			return ViewModelProvider(
				viewModelStoreOwner,
				viewModelFactory
			)[_M_NAME_PASCAL_ViewModelImpl::class.java]
		}
	}
}