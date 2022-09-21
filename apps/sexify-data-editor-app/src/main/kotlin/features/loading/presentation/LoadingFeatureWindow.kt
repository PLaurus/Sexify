package features.loading.presentation

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.window.Window
import application.presentation.values.Dimens
import features.loading.di.modules.entities.LoadingState
import ui.theme.SexifyDataEditorAppTheme

@Composable
fun LoadingWindow(
	state: LoadingWindowState
) {
	Window(
		onCloseRequest = state.onCloseRequest,
	) {
		Box(
			modifier = Modifier
				.fillMaxSize()
				.padding(Dimens.LayoutSpacing8)
		) {
			Content(
				loadingState = state.loadingState,
				modifier = Modifier.fillMaxSize()
			)
		}
	}
}

@Composable
private fun Content(
	loadingState: LoadingState,
	modifier: Modifier = Modifier
) {
	Box(
		modifier = modifier
	) {
		if (loadingState == LoadingState.Loading) {
			CircularProgressIndicator(
				modifier = modifier
					.wrapContentSize()
					.align(Alignment.Center)
			)
		}
	}
}

@Preview
@Composable
private fun ContentPreview() {
	SexifyDataEditorAppTheme {
		Content(
			loadingState = LoadingState.Loading
		)
	}
}