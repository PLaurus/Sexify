package com.lauruscorp.sexify_android.features.main.di.component

import android.content.Context
import androidx.lifecycle.ViewModelStoreOwner
import com.lauruscorp.core_android.di.dagger.qualifiers.context.ActivityContextQualifier
import com.lauruscorp.features.maindomain.api.MainDomainDependencies
import com.lauruscorp.sexify_android.application.di.component.SexifyApplicationComponent
import com.lauruscorp.sexify_android.features.categoriesselection.api.CategoriesSelectionFeatureDependencies
import com.lauruscorp.sexify_android.features.couplenameseditor.api.CoupleNamesEditorFeatureDependencies
import com.lauruscorp.sexify_android.features.game.api.GameFeatureDependencies
import com.lauruscorp.sexify_android.features.main.MainActivity
import com.lauruscorp.sexify_android.features.main.di.component.scope.MainActivityScope
import com.lauruscorp.sexify_android.features.main.di.modules.childfragments.MainChildFragmentsModule
import com.lauruscorp.sexify_android.features.main.di.modules.domain.MainDomainModule
import com.lauruscorp.sexify_android.features.main.di.modules.mappers.MainMappersModule
import com.lauruscorp.sexify_android.features.main.di.modules.ui.MainUiModule
import com.lauruscorp.sexify_android.features.main.di.modules.viewmodel.MainViewModelModule
import com.lauruscorp.sexify_android.features.menu.api.MenuFeatureDependencies
import dagger.BindsInstance
import dagger.Subcomponent

@MainActivityScope
@Subcomponent(
    modules = [
        MainUiModule::class,
        MainDomainModule::class,
        MainViewModelModule::class,
        MainMappersModule::class,
        MainChildFragmentsModule::class
    ]
)
internal interface MainActivityComponent : MainDomainDependencies,
                                           CategoriesSelectionFeatureDependencies,
                                           CoupleNamesEditorFeatureDependencies,
                                           GameFeatureDependencies,
                                           MenuFeatureDependencies {
    fun inject(activity: MainActivity)

    @Subcomponent.Factory
    interface Factory {
        fun create(
            @BindsInstance @ActivityContextQualifier activityContext: Context,
            @BindsInstance viewModelStoreOwner: ViewModelStoreOwner
        ): MainActivityComponent
    }

    companion object {
        operator fun invoke(
            sexifyApplicationComponent: SexifyApplicationComponent,
            activityContext: Context,
            viewModelStoreOwner: ViewModelStoreOwner
        ): MainActivityComponent {
            return sexifyApplicationComponent
                .getMainActivityComponentFactory()
                .create(
                    activityContext = activityContext,
                    viewModelStoreOwner = viewModelStoreOwner
                )
        }
    }
}