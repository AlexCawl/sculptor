package org.alexcawl.sculptor.showroom

import androidx.compose.foundation.text.BasicText
import androidx.compose.ui.Modifier
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import org.alexcawl.sculptor.runtime.engine.Sculptor
import org.alexcawl.sculptor.runtime.engine.SculptorScreen
import org.alexcawl.sculptor.runtime.engine.SculptorStringIntent
import org.alexcawl.sculptor.runtime.engine.contractor
import org.alexcawl.sculptor.runtime.engine.dependencies.dataSource.ContentResolutionStrategy
import org.alexcawl.sculptor.runtime.engine.presentation.rememberSculptorScreen
import org.alexcawl.sculptor.runtime.engine.presenter
import org.alexcawl.sculptor.runtime.engine.renderer
import org.alexcawl.sculptor.showroom.components.ContainerPresenter
import org.alexcawl.sculptor.showroom.components.ContainerRenderer
import org.alexcawl.sculptor.showroom.components.TextPresenter
import org.alexcawl.sculptor.showroom.components.TextRenderer
import org.alexcawl.sculptor.showroom.dependencies.ShowroomContractor
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
        contractor {
            ShowroomContractor
        }
        presenter {
            TextPresenter
        }
        presenter {
            ContainerPresenter
        }
        renderer {
            TextRenderer
        }
        renderer {
            ContainerRenderer
        }
    }

    // Create
    val sculptor: Sculptor = Sculptor.create {  }

    // Main
    application {
        val sculptorScreen: SculptorScreen = rememberSculptorScreen(sculptor)
        Window(::exitApplication) {
            sculptorScreen.provides {
                SculptorScreen(
                    intent = SculptorStringIntent("files/screen_hello_world.json"),
                    loadingScreen = {
                        BasicText(text = "Loading")
                    },
                    errorScreen = {
                        BasicText(text = "Error")
                    },
                    modifier = Modifier,
                )
            }
        }
    }
}
