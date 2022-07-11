package com.lauruscorp.sexifyapp.features.couplenameseditor.di.modules.ui

import android.view.View
import com.lauruscorp.core.ui.ViewBindingUi
import com.lauruscorp.sexifyapp.databinding.LayoutCoupleNamesEditorBinding
import com.lauruscorp.sexifyapp.features.couplenameseditor.ui.CoupleNamesEditorUi
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
internal abstract class UiModule {
	@Binds
	abstract fun provideCoupleNamesEditorUi(
		ui: CoupleNamesEditorUi
	): @JvmSuppressWildcards ViewBindingUi<LayoutCoupleNamesEditorBinding>
	
	companion object {
		@Provides
		fun provideViewBindingProvider(): (view: View) -> LayoutCoupleNamesEditorBinding {
			return LayoutCoupleNamesEditorBinding::bind
		}
	}
}