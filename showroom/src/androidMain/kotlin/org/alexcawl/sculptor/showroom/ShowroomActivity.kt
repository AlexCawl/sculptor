package org.alexcawl.sculptor.showroom

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.text.BasicText
import androidx.compose.ui.Modifier
import org.alexcawl.sculptor.runtime.engine.SculptorScreen
import org.alexcawl.sculptor.runtime.engine.SculptorStringIntent
import org.alexcawl.sculptor.runtime.engine.presentation.sculptorScreen

public class ShowroomActivity : ComponentActivity() {
    private val sculptorScreen: SculptorScreen by sculptorScreen()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            sculptorScreen.open(
                intent = SculptorStringIntent(payload = "files/screen_hello_world.json"),
                loadingScreen = {
                    BasicText(text = "Loading")
                },
                errorScreen = {
                    BasicText(text = "Error")
                },
                modifier = Modifier
            )
        }
    }
}

