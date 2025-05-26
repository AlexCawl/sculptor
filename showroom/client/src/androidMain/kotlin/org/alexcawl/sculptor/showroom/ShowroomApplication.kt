package org.alexcawl.sculptor.showroom

import android.app.Application
import org.alexcawl.sculptor.foundation.client.FoundationBundle
import org.alexcawl.sculptor.runtime.engine.Sculptor
import org.alexcawl.sculptor.runtime.engine.contractor
import org.alexcawl.sculptor.runtime.engine.dependencies.ContentResolutionStrategy
import org.alexcawl.sculptor.runtime.engine.presenter
import org.alexcawl.sculptor.runtime.engine.renderer
import org.alexcawl.sculptor.showroom.components.ContainerPresenter
import org.alexcawl.sculptor.showroom.components.ContainerRenderer
import org.alexcawl.sculptor.showroom.dependencies.ShowroomContractor
import org.alexcawl.sculptor.showroom.dependencies.ShowroomLogger
import org.alexcawl.sculptor.showroom.dependencies.ShowroomRemoteContentSource
import org.alexcawl.sculptor.showroom.components.TextPresenter
import org.alexcawl.sculptor.showroom.components.TextRenderer

public class ShowroomApplication : Application() {
    override fun onCreate() {
        super.onCreate()
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
    }
}
