package org.alexcawl.sculptor.showroom

import androidx.compose.foundation.text.BasicText
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import org.alexcawl.sculptor.runtime.engine.SculptorStringIntent
import org.alexcawl.sculptor.runtime.engine.SculptorUi

@Composable
public fun ShowroomApp(
    sculptorUiProvider: () -> SculptorUi,
    modifier: Modifier = Modifier,
) {
    val sculptorUi: SculptorUi = remember(key1 = Unit, calculation = sculptorUiProvider)
    sculptorUi.Screen(
        intent = SculptorStringIntent("files/screen_hello_world.json"),
        loadingScreen = {
            BasicText(text = "Loading")
        },
        errorScreen = {
            BasicText(text = "Error")
        },
        modifier = modifier,
    )
}
