package org.alexcawl.skulptor.foundation.layout.row

import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.util.fastForEach
import org.alexcawl.skulptor.core.layout.ContainerLayoutFactory

object RowFactory : ContainerLayoutFactory<RowState>() {
    override fun build(state: RowState, modifier: Modifier): @Composable () -> Unit = {
        Row(
            modifier = modifier,
            horizontalArrangement = state.horizontalArrangement.invoke(),
            verticalAlignment = state.verticalAlignment.invoke(),
            content = {
                state.content.fastForEach {
                    place(layoutId = it, scope = this)
                }
            }
        )
    }
}
