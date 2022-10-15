package com.lauruscorp.sexify_android.features.loading.presentation.viewmodel

import androidx.lifecycle.LiveData
import com.lauruscorp.sexify_domain.loading.entities.LoadingState

internal interface LoadingViewModel {
    val loadingState: LiveData<LoadingState>
}