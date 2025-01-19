package org.alexcawl.skulptor.foundation.layout.column

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.util.fastForEach
import org.alexcawl.skulptor.core.layout.ContainerLayoutFactory

object ColumnFactory : ContainerLayoutFactory<ColumnState>() {
    override fun build(state: ColumnState, modifier: Modifier): @Composable () -> Unit = {
        Column(
            modifier = modifier,
            verticalArrangement = state.verticalArrangement.invoke(),
            horizontalAlignment = state.horizontalAlignment.invoke(),
            content = {
                state.content.fastForEach {
                    place(layoutId = it, scope = this)
                }
            }
        )
    }
}
