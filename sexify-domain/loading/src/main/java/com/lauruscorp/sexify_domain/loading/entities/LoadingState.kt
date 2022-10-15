package com.lauruscorp.sexify_domain.loading.entities

sealed interface LoadingState {
    object Loading : LoadingState
    object Loaded : LoadingState
    data class Error(val errorMessage: String) : LoadingState
}