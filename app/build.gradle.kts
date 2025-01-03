import org.alexcawl.plugins.commonMainDependencies
import org.alexcawl.plugins.jvm.desktop

plugins {
    id("convention.project.kmp.application")
}

android {
    namespace = "org.alexcawl.demo.app"

    defaultConfig {
        applicationId = "org.alexcawl.demo.app"
    }
}

desktop(
    applicationClass = "org.alexcawl.demo.app.MainKt",
    applicationPackage = "org.alexcawl.demo.app"
)

commonMainDependencies {
    implementation(project(":common"))
}
