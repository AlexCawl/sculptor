import org.alexcawl.plugins.commonMainDependencies
import org.alexcawl.plugins.jvm.desktop

plugins {
    id("convention.project.showroom.application")
}

android {
    namespace = "org.alexcawl.sculptor.showroom"

    defaultConfig {
        applicationId = "org.alexcawl.sculptor.showroom"
    }
}

desktop(
    applicationClass = "org.alexcawl.sculptor.showroom.MainKt",
    applicationPackage = "org.alexcawl.sculptor.showroom"
)

commonMainDependencies {
    implementation(projects.runtime.engine)
    implementation(projects.foundation.bundle)
}
