import org.alexcawl.plugins.base.commonMainDependencies
import org.alexcawl.plugins.base.configurations.desktop

plugins {
    id("convention.client.application")
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
