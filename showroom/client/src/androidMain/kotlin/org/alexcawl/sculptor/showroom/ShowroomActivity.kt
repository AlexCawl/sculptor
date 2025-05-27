package org.alexcawl.sculptor.showroom

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import org.alexcawl.sculptor.runtime.engine.SculptorUi
import org.alexcawl.sculptor.runtime.engine.presentation.sculptorUi

public class ShowroomActivity : ComponentActivity() {
    private val sculptorUi: SculptorUi by sculptorUi()
    private val screenState: MutableStateFlow<Screen> = MutableStateFlow(Screen.TEXTS)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val screen: Screen by screenState.collectAsState()
            ShowroomApp(
                screen = screen,
                onScreenChange = screenState::tryEmit,
                sculptorUiProvider = { sculptorUi },
                modifier = Modifier.fillMaxSize(),
            )
        }
    }
}
