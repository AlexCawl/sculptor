package org.alexcawl.skulptor.foundation.layout.basictext

import androidx.compose.foundation.text.BasicText
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import org.alexcawl.skulptor.core.layout.SolidLayoutFactory

object BasicTextFactory : SolidLayoutFactory<BasicTextState>() {
    @Composable
    override fun build(state: BasicTextState, modifier: Modifier) {
        BasicText(
            text = state.text,
            modifier = modifier,
            softWrap = state.softWrap,
            maxLines = state.maxLines,
            minLines = state.minLines
        )
    }
}
