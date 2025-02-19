package org.alexcawl.sculptor.engine

import androidx.compose.animation.AnimatedContent
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

@Composable
public fun Sculptor(
    sculptorState: SculptorState,
    launchMode: LaunchMode,
    loading: @Composable () -> Unit,
    error: @Composable () -> Unit,
    modifier: Modifier = Modifier,
) {
    var uiState: SculptorUiState by remember(key1 = launchMode, key2 = sculptorState) {
        mutableStateOf(value = SculptorUiState.Loading)
    }

    LaunchedEffect(key1 = launchMode, key2 = sculptorState) {
        val sculptorScreen: Result<SculptorScreen> = withContext(Dispatchers.Default) {
            sculptorState.launch(mode = launchMode)
        }
        uiState = when (val value = sculptorScreen.getOrNull()) {
            null -> SculptorUiState.Error
            else -> SculptorUiState.Content(value)
        }
    }

    AnimatedContent(
        targetState = uiState,
        modifier = modifier
    ) { state ->
        when (state) {
            SculptorUiState.Loading -> loading()
            SculptorUiState.Error -> error()
            is SculptorUiState.Content -> state.screen()
        }
    }
}

@Immutable
private sealed interface SculptorUiState {
    data object Loading : SculptorUiState

    data object Error : SculptorUiState

    data class Content(val screen: SculptorScreen) : SculptorUiState
}
