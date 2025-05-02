package org.alexcawl.sculptor.showroom

import android.app.Application
import org.alexcawl.sculptor.runtime.engine.Sculptor
import org.alexcawl.sculptor.runtime.engine.contractor
import org.alexcawl.sculptor.runtime.engine.dependencies.dataSource.ContentResolutionStrategy
import org.alexcawl.sculptor.runtime.engine.presenter
import org.alexcawl.sculptor.runtime.engine.renderer
import org.alexcawl.sculptor.showroom.dependencies.ContainerPresenter
import org.alexcawl.sculptor.showroom.dependencies.ContainerRenderer
import org.alexcawl.sculptor.showroom.dependencies.ShowroomContractor
import org.alexcawl.sculptor.showroom.dependencies.ShowroomLogger
import org.alexcawl.sculptor.showroom.dependencies.ShowroomRemoteContentSource
import org.alexcawl.sculptor.showroom.dependencies.TextPresenter
import org.alexcawl.sculptor.showroom.dependencies.TextRenderer

public class ShowroomApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        Sculptor.initialize {
            contentResolutionStrategy {
                ContentResolutionStrategy.RemoteFirst
            }
            remoteContentSource {
                ShowroomRemoteContentSource(context = this@ShowroomApplication)
            }
            sculptorLogger {
                ShowroomLogger
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
    }
}
