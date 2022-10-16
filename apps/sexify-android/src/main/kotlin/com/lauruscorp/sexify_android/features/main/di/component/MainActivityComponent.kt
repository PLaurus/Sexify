package com.lauruscorp.sexify_android.features.main.di.component

import android.content.Context
import androidx.lifecycle.ViewModelStoreOwner
import com.lauruscorp.core.di.dagger.qualifiers.context.ActivityContextQualifier
import com.lauruscorp.features.maindomain.dependencies.MainDomainDependencies
import com.lauruscorp.sexify_android.application.di.component.SexifyApplicationComponent
import com.lauruscorp.sexify_android.features.categoriesselection.api.CategoriesSelectionFeatureDependencies
import com.lauruscorp.sexify_android.features.couplenameseditor.dependencies.CoupleNamesEditorFeatureDependencies
import com.lauruscorp.sexify_android.features.game.api.GameFeatureDependencies
import com.lauruscorp.sexify_android.features.main.MainActivity
import com.lauruscorp.sexify_android.features.main.di.component.scope.MainActivityScope
import com.lauruscorp.sexify_android.features.main.di.modules.childfragments.MainChildFragmentsModule
import com.lauruscorp.sexify_android.features.main.di.modules.domain.MainDomainModule
import com.lauruscorp.sexify_android.features.main.di.modules.mappers.MainMappersModule
import com.lauruscorp.sexify_android.features.main.di.modules.plugged_in_features.PluggedInFeaturesModule
import com.lauruscorp.sexify_android.features.main.di.modules.ui.MainUiModule
import com.lauruscorp.sexify_android.features.main.di.modules.viewmodel.MainViewModelModule
import dagger.BindsInstance
import dagger.Subcomponent

@MainActivityScope
@Subcomponent(
    modules = [
        MainUiModule::class,
        MainDomainModule::class,
        MainViewModelModule::class,
        MainMappersModule::class,
        PluggedInFeaturesModule::class,
        MainChildFragmentsModule::class
    ]
)
internal interface MainActivityComponent : MainDomainDependencies,
    CategoriesSelectionFeatureDependencies,
    CoupleNamesEditorFeatureDependencies,
    GameFeatureDependencies {

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