package com.lauruscorp.sexify_android.features.loading.presentation.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import com.lauruscorp.sexify_domain.loading.entities.LoadingState

@Preview(
    showSystemUi = true,
    showBackground = true,
    device = Devices.PIXEL_4
)
@Composable
private fun LoadingScreenPreview() {
    LoadingFeatureScreen(
        loadingState = LoadingState.Loading,
        modifier = Modifier.fillMaxSize()
    )
}

@Composable
internal fun LoadingFeatureScreen(
    loadingState: LoadingState,
    modifier: Modifier = Modifier
) {
    Box(modifier) {
        when (loadingState) {
            LoadingState.Loading -> {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            }
            is LoadingState.Error -> {
                ErrorText(loadingState.errorMessage)
            }
            else -> Unit
        }
    }
}

@Composable
private fun ErrorText(
    error: String,
    modifier: Modifier = Modifier
) {
    Text(text = error, modifier = modifier, color = MaterialTheme.colorScheme.error)
}