import org.alexcawl.plugins.commonMainDependencies
import org.alexcawl.plugins.jvm.desktop

plugins {
    id("convention.project.showroom.application")
}

android {
    namespace = "org.alexcawl.sculptor.foundation.showroom"

    defaultConfig {
        applicationId = "org.alexcawl.sculptor.foundation.showroom"
    }
}

desktop(
    applicationClass = "org.alexcawl.sculptor.foundation.showroom.MainKt",
    applicationPackage = "org.alexcawl.sculptor.foundation.showroom"
)

commonMainDependencies {
    implementation(projects.runtime.engine)
    implementation(projects.foundation.bundle)
}
