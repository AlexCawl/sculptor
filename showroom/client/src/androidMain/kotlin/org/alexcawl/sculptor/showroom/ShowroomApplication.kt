package org.alexcawl.sculptor.showroom

import android.app.Application
import org.alexcawl.sculptor.foundation.client.FoundationBundle
import org.alexcawl.sculptor.runtime.engine.Sculptor
import org.alexcawl.sculptor.runtime.engine.dependencies.ContentResolutionStrategy
import org.alexcawl.sculptor.showroom.dependencies.ShowroomLogger
import org.alexcawl.sculptor.showroom.dependencies.ShowroomRemoteContentSource

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
