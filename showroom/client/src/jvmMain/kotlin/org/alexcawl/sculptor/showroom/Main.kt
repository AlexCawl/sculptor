package org.alexcawl.sculptor.showroom

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import kotlinx.coroutines.flow.MutableStateFlow
import org.alexcawl.sculptor.foundation.client.FoundationBundle
import org.alexcawl.sculptor.runtime.engine.Sculptor
import org.alexcawl.sculptor.runtime.engine.SculptorUi
import org.alexcawl.sculptor.runtime.engine.dependencies.ContentResolutionStrategy
import org.alexcawl.sculptor.runtime.engine.presentation.rememberSculptorUi
import org.alexcawl.sculptor.showroom.dependencies.ShowroomLogger
import org.alexcawl.sculptor.showroom.dependencies.ShowroomRemoteContentSource

public fun main() {
    // Init
    Sculptor.initialize {
        contentResolutionStrategy {
            ContentResolutionStrategy.RemoteFirst
        }
        remoteContentSource {
            ShowroomRemoteContentSource()
        }
        sculptorLogger {
            ShowroomLogger()
        }
        with(FoundationBundle) { install() }
    }

    // Create
    val sculptor: Sculptor = Sculptor.create { }
    val screenState: MutableStateFlow<Screen> = MutableStateFlow(Screen.TEXTS)

    // Main
    application {
        val sculptorUi: SculptorUi = rememberSculptorUi(sculptor)
        Window(::exitApplication) {
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
