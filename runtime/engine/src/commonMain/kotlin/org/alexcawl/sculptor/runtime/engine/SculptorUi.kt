package org.alexcawl.sculptor.runtime.engine

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.ui.Modifier

@Stable
public interface SculptorUi {
    @Composable
    public fun Screen(
        intent: SculptorIntent,
        loadingScreen: @Composable () -> Unit,
        errorScreen: @Composable () -> Unit,
        modifier: Modifier,
    )
}
