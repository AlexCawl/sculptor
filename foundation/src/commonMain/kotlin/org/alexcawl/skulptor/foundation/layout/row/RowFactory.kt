package org.alexcawl.skulptor.foundation.layout.row

import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.util.fastForEach
import org.alexcawl.skulptor.core.layout.CompositeLayoutFactory

object RowFactory : CompositeLayoutFactory<RowState>() {
    @Composable
    override fun build(state: RowState, modifier: Modifier) {
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
