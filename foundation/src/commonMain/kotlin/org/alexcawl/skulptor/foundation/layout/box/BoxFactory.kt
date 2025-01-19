package org.alexcawl.skulptor.foundation.layout.box

import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.util.fastForEach
import org.alexcawl.skulptor.core.layout.CompositeLayoutFactory

object BoxFactory : CompositeLayoutFactory<BoxState>() {
    @Composable
    override fun build(state: BoxState, modifier: Modifier) {
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
