package com.lauruscorp.sexassistant.di

import com.lauruscorp.sexassistant.presentation.SexAssistantActivity
import dagger.Component

@Component
internal interface SexAssistantComponent {
	@Component.Factory
	interface Factory {
		fun create(): SexAssistantComponent
	}
	
	fun inject(activity: SexAssistantActivity)
}