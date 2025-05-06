package org.alexcawl.sculptor.showroom

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import org.alexcawl.sculptor.runtime.engine.SculptorUi
import org.alexcawl.sculptor.runtime.engine.presentation.sculptorUi

public class ShowroomActivity : ComponentActivity() {
    private val sculptorUi: SculptorUi by sculptorUi()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            ShowroomApp(
                sculptorUiProvider = { sculptorUi },
                modifier = Modifier.fillMaxSize(),
            )
        }
    }
}
