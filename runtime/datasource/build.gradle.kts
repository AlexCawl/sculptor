import org.alexcawl.plugins.base.androidMainDependencies
import org.alexcawl.plugins.base.commonMainDependencies
import org.alexcawl.plugins.base.jvmMainDependencies
import org.alexcawl.plugins.base.wasmJsMainDependencies

plugins {
    id("convention.client.library")
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
