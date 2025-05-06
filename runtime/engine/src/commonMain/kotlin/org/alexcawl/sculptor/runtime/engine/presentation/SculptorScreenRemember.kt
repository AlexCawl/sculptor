package org.alexcawl.sculptor.runtime.engine.presentation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import org.alexcawl.sculptor.runtime.engine.Sculptor
import org.alexcawl.sculptor.runtime.engine.SculptorUi

@Stable
@Composable
public fun rememberSculptorUi(sculptor: Sculptor): SculptorUi {
    return remember(key1 = sculptor) {
        SculptorUiRememberImpl(sculptorConnector = sculptor.connector)
    }
}
