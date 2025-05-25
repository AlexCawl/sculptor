package org.alexcawl.sculptor.showroom

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.window.ComposeViewport
import kotlinx.browser.document
import org.alexcawl.sculptor.foundation.client.FoundationBundle
import org.alexcawl.sculptor.runtime.engine.Sculptor
import org.alexcawl.sculptor.runtime.engine.SculptorUi
import org.alexcawl.sculptor.runtime.engine.contractor
import org.alexcawl.sculptor.runtime.engine.dependencies.ContentResolutionStrategy
import org.alexcawl.sculptor.runtime.engine.presentation.rememberSculptorUi
import org.alexcawl.sculptor.runtime.engine.presenter
import org.alexcawl.sculptor.runtime.engine.renderer
import org.alexcawl.sculptor.showroom.components.ContainerPresenter
import org.alexcawl.sculptor.showroom.components.ContainerRenderer
import org.alexcawl.sculptor.showroom.components.TextPresenter
import org.alexcawl.sculptor.showroom.components.TextRenderer
import org.alexcawl.sculptor.showroom.dependencies.ShowroomContractor
import org.alexcawl.sculptor.showroom.dependencies.ShowroomLogger
import org.alexcawl.sculptor.showroom.dependencies.ShowroomRemoteContentSource

@OptIn(ExperimentalComposeUiApi::class)
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
        with(FoundationBundle) { install() }
    }

    // Create
    val sculptor: Sculptor = Sculptor.create {  }

    ComposeViewport(document.body!!) {
        val sculptorUi: SculptorUi = rememberSculptorUi(sculptor)
        ShowroomApp(
            sculptorUiProvider = { sculptorUi },
            modifier = Modifier.fillMaxSize(),
        )
    }
}
