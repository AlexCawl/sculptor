package org.alexcawl.sculptor.common.renderer

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.ui.Modifier
import org.alexcawl.sculptor.common.layout.UiState
import kotlin.reflect.KClass

@Stable
public abstract class Renderer<S : UiState> {
    public abstract val state: KClass<S>

    @Stable
    @Composable
    public abstract fun Draw(scope: RendererScope, id: String, modifier: Modifier, state: S)
}
