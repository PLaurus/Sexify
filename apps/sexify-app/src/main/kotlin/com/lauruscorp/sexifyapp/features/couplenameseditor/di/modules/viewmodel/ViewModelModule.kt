package com.lauruscorp.sexifyapp.features.couplenameseditor.di.modules.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import com.lauruscorp.core_android.di.dagger.mapkeys.ViewModelMapKey
import com.lauruscorp.sexifyapp.features.couplenameseditor.viewmodel.CoupleNamesEditorViewModel
import com.lauruscorp.sexifyapp.features.couplenameseditor.viewmodel.CoupleNamesEditorViewModelImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap

@Module
internal abstract class ViewModelModule {
    @Binds
    @IntoMap
    @ViewModelMapKey(CoupleNamesEditorViewModelImpl::class)
    abstract fun bindCoupleNamesEditorViewModelIntoMap(
        coupleNamesEditorViewModel: CoupleNamesEditorViewModelImpl
    ): ViewModel

    companion object {
        @Provides
        fun provideCoupleNamesEditorViewModel(
            viewModelStoreOwner: ViewModelStoreOwner,
            viewModelFactory: ViewModelProvider.Factory
        ): CoupleNamesEditorViewModel {
            return ViewModelProvider(
                viewModelStoreOwner,
                viewModelFactory
            )[CoupleNamesEditorViewModelImpl::class.java]
        }
    }
}