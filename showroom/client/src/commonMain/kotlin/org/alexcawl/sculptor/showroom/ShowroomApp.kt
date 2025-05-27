package org.alexcawl.sculptor.showroom

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicText
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import org.alexcawl.sculptor.runtime.engine.SculptorStringIntent
import org.alexcawl.sculptor.runtime.engine.SculptorUi

@Composable
public fun ShowroomApp(
    screen: Screen,
    onScreenChange: (Screen) -> Unit,
    sculptorUiProvider: () -> SculptorUi,
    modifier: Modifier = Modifier,
) {
    val sculptorUi: SculptorUi = remember(key1 = Unit, calculation = sculptorUiProvider)
    Box(modifier = modifier) {
        when (screen) {
            Screen.TEXTS -> {
                sculptorUi.Screen(
                    intent = SculptorStringIntent("files/screen_with_texts.json"),
                    loadingScreen = {
                        BasicText(text = "Loading")
                    },
                    errorScreen = {
                        BasicText(text = "Error")
                    },
                    modifier = Modifier.fillMaxWidth().height(IntrinsicSize.Min),
                )
            }
            Screen.FEED -> {

            }
        }

        Row(
            modifier = Modifier.align(Alignment.BottomStart).fillMaxWidth().padding(16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Screen.entries.forEach {
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .height(64.dp)
                        .clickable { onScreenChange(it) }
                        .background(
                            color = Color.LightGray,
                            shape = RoundedCornerShape(size = 16.dp),
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    BasicText(text = it.name)
                }
            }
        }
    }
}
