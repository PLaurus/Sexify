package com.lauruscorp.features.example.exampleapp.activity.di.modules

import android.view.View
import com.lauruscorp.core.ui.ViewBindingUi
import com.lauruscorp.features.example.exampleapp.activity.ui.ExampleAppUi
import com.lauruscorp.features.example.exampleapp.databinding.LayoutExampleAppBinding
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
internal abstract class UiModule {
	@Binds
	abstract fun provideExampleAppUi(
		ui: ExampleAppUi
	): @JvmSuppressWildcards ViewBindingUi<LayoutExampleAppBinding>
	
	companion object {
		@Provides
		fun provideViewBindingProvider(): (view: View) -> LayoutExampleAppBinding {
			return LayoutExampleAppBinding::bind
		}
	}
}