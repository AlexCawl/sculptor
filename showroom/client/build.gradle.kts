import org.alexcawl.plugins.base.commonMainDependencies
import org.alexcawl.plugins.base.configurations.desktop

plugins {
    id("convention.client.application")
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
    implementation(projects.runtime.datasource)
    implementation(projects.foundation.bundle)
}
