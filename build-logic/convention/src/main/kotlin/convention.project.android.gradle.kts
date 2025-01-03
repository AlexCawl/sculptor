import org.alexcawl.plugins.androidTestImplementation
import org.alexcawl.plugins.implementation
import org.alexcawl.plugins.libs
import org.alexcawl.plugins.testImplementation

plugins {
    id("org.jetbrains.kotlin.android")
    id("org.jetbrains.compose")
    id("org.jetbrains.kotlin.plugin.compose")
    id("io.gitlab.arturbosch.detekt")

    id("convention.base.common")
    id("convention.base.android")
    id("convention.base.detekt")
}

dependencies {
    implementation(libs.bundles.common.source)
    testImplementation(libs.bundles.common.test)
    implementation(libs.bundles.android.source)
    androidTestImplementation(libs.bundles.android.test)

    implementation(compose.runtime)
    implementation(compose.foundation)
    implementation(compose.material)
    implementation(compose.ui)
    implementation(compose.preview)
}
