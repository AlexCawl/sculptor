package org.alexcawl.sculptor.runtime.engine.presentation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import org.alexcawl.sculptor.runtime.engine.Sculptor
import org.alexcawl.sculptor.runtime.engine.SculptorScreen

@Stable
@Composable
public fun rememberSculptorScreen(sculptor: Sculptor): SculptorScreen {
    return remember(key1 = sculptor) {
        SculptorScreenRememberImpl(sculptorConnector = sculptor.connector)
    }
}
