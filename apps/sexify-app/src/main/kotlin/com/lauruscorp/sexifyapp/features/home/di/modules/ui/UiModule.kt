package com.lauruscorp.sexifyapp.features.home.di.modules.ui

import android.view.View
import com.lauruscorp.core.ui.ViewBindingUi
import com.lauruscorp.sexifyapp.databinding.LayoutHomeBinding
import com.lauruscorp.sexifyapp.features.home.ui.HomeUi
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
internal abstract class UiModule {
	@Binds
	abstract fun provideHomeUi(
		ui: HomeUi
	): @JvmSuppressWildcards ViewBindingUi<LayoutHomeBinding>
	
	companion object {
		@Provides
		fun provideViewBindingProvider(): (view: View) -> LayoutHomeBinding {
			return LayoutHomeBinding::bind
		}
	}
}