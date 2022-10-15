package com.lauruscorp.sexify_android.features.loading.di.component

import androidx.lifecycle.ViewModelStoreOwner
import com.lauruscorp.sexify_android.features.loading.dependencies.LoadingFeatureDependencies
import com.lauruscorp.sexify_android.features.loading.di.component.scope.LoadingFeatureScope
import com.lauruscorp.sexify_android.features.loading.di.modules.domain.DomainModule
import com.lauruscorp.sexify_android.features.loading.di.modules.viewmodel.ViewModelModule
import com.lauruscorp.sexify_android.features.loading.presentation.LoadingFragment
import dagger.BindsInstance
import dagger.Component

@LoadingFeatureScope
@Component(
    dependencies = [
        LoadingFeatureDependencies::class
    ],
    modules = [
        ViewModelModule::class,
        DomainModule::class
    ]
)
internal interface LoadingFeatureComponent {
    fun inject(fragment: LoadingFragment)

    @Component.Factory
    interface Factory {
        fun create(
            dependencies: LoadingFeatureDependencies,
            @BindsInstance viewModelStoreOwner: ViewModelStoreOwner
        ): LoadingFeatureComponent
    }

    companion object {
        operator fun invoke(
            dependencies: LoadingFeatureDependencies,
            viewModelStoreOwner: ViewModelStoreOwner
        ): LoadingFeatureComponent {
            return DaggerLoadingFeatureComponent.factory()
                .create(
                    dependencies = dependencies,
                    viewModelStoreOwner = viewModelStoreOwner
                )
        }
    }
}