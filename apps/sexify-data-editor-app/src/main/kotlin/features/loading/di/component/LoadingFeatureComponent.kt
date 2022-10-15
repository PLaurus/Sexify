package features.loading.di.component

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import com.lauruscorp.core_jvm.di.dagger.qualifiers.mvi.InitialStateQualifier
import dagger.Component
import features.loading.dependencies.LoadingFeatureDependencies
import features.loading.di.component.scope.LoadingFeatureScope
import features.loading.di.modules.store.LoadingFeatureStoreModule
import features.loading.domain.store.LoadingFeatureStore

@LoadingFeatureScope
@Component(
	dependencies = [
		LoadingFeatureDependencies::class
	],
	modules = [
		LoadingFeatureStoreModule::class
	]
)
internal interface LoadingFeatureComponent {
	fun getLoadingFeatureStore(): LoadingFeatureStore
	
	@InitialStateQualifier
	fun getLoadingFeatureStoreInitialState(): LoadingFeatureStore.State
	
	@Component.Factory
	interface Factory {
		fun create(
			dependencies: LoadingFeatureDependencies
		): LoadingFeatureComponent
	}
	
	companion object {
		operator fun invoke(
			dependencies: LoadingFeatureDependencies
		): LoadingFeatureComponent {
			return DaggerLoadingFeatureComponent.factory()
				.create(dependencies)
		}
	}
}

@Composable
internal fun rememberLoadingFeatureComponent(
	dependencies: LoadingFeatureDependencies
): LoadingFeatureComponent {
	return remember { LoadingFeatureComponent(dependencies) }
}