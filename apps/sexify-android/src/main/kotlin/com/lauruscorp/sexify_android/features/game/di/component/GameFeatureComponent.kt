package com.lauruscorp.sexify_android.features.game.di.component

import androidx.lifecycle.ViewModelStoreOwner
import com.lauruscorp.features.gamedomain.api.GameDomainDependencies
import com.lauruscorp.sexify_android.features.game.api.GameFeatureDependencies
import com.lauruscorp.sexify_android.features.game.api.presentation.GameFragment
import com.lauruscorp.sexify_android.features.game.di.component.scope.GameFeatureScope
import com.lauruscorp.sexify_android.features.game.di.modules.domain.DomainModule
import com.lauruscorp.sexify_android.features.game.di.modules.mappers.MappersModule
import com.lauruscorp.sexify_android.features.game.di.modules.ui.UiModule
import com.lauruscorp.sexify_android.features.game.di.modules.viewmodel.ViewModelModule
import dagger.BindsInstance
import dagger.Component

@GameFeatureScope
@Component(
	dependencies = [
		GameFeatureDependencies::class
	],
	modules = [
		UiModule::class,
		ViewModelModule::class,
		MappersModule::class,
		DomainModule::class
	]
)
internal interface GameFeatureComponent : GameDomainDependencies {
	fun inject(fragment: GameFragment)
	
	@Component.Factory
	interface Factory {
		fun create(
			dependencies: GameFeatureDependencies,
			@BindsInstance viewModelStoreOwner: ViewModelStoreOwner
		): GameFeatureComponent
	}
	
	companion object {
		operator fun invoke(
			dependencies: GameFeatureDependencies,
			viewModelStoreOwner: ViewModelStoreOwner
		): GameFeatureComponent {
			return DaggerGameFeatureComponent.factory()
				.create(
					dependencies = dependencies,
					viewModelStoreOwner = viewModelStoreOwner
				)
		}
	}
}