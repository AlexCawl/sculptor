import org.alexcawl.plugins.androidMainDependencies
import org.alexcawl.plugins.commonMainDependencies
import org.alexcawl.plugins.jvmMainDependencies
import org.alexcawl.plugins.wasmJsMainDependencies

plugins {
    id("convention.project.kotlin.library")
}

android {
    namespace = "org.alexcawl.sculptor.runtime.datasource"
}

commonMainDependencies {
    implementation(projects.runtime.engine)
    implementation(libs.common.ktor.client)
}

androidMainDependencies {
    implementation(libs.android.datastore)
}

jvmMainDependencies {
    implementation(libs.jvm.datastore)
}

wasmJsMainDependencies {
    implementation(libs.wasmJs.browser)
}
