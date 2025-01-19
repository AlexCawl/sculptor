package org.alexcawl.skulptor.foundation.layout.box

import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.util.fastForEach
import org.alexcawl.skulptor.core.layout.ContainerLayoutFactory

object BoxFactory : ContainerLayoutFactory<BoxState>() {
    override fun build(state: BoxState, modifier: Modifier): @Composable () -> Unit = {
        Box(
            modifier = modifier,
            contentAlignment = state.contentAlignment.invoke(),
            propagateMinConstraints = state.propagateMinConstraints,
            content = {
                state.content.fastForEach {
                    place(layoutId = it, scope = this)
                }
            }
        )
    }
}
