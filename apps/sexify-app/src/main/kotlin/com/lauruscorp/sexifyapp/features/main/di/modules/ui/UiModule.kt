package com.lauruscorp.sexifyapp.features.main.di.modules.ui

import android.view.View
import com.lauruscorp.core.ui.ViewBindingUi
import com.lauruscorp.sexifyapp.databinding.LayoutMainBinding
import com.lauruscorp.sexifyapp.features.main.presentation.ui.MainUi
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
internal abstract class UiModule {
	@Binds
	abstract fun provideMainUi(
		ui: MainUi
	): @JvmSuppressWildcards ViewBindingUi<LayoutMainBinding>
	
	companion object {
		@Provides
		fun provideViewBindingProvider(): (view: View) -> LayoutMainBinding {
			return LayoutMainBinding::bind
		}
	}
}