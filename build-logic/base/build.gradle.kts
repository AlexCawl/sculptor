plugins {
    `kotlin-dsl`
}

kotlin {
    explicitApi()
}

dependencies {
    // Kotlin plugins
    implementation(libs.gradleplugin.kotlinMultiplatform)
    implementation(libs.gradleplugin.composeMultiplatform)
    implementation(libs.gradleplugin.composeCompiler)
    implementation(libs.gradleplugin.kotlinSerialization)

    // Android plugins
    implementation(libs.gradleplugin.android)

    // Jvm plugins
    implementation(libs.gradleplugin.kotlinJvm)

    // Utility plugins
    implementation(libs.gradleplugin.detekt)
    implementation(libs.gradleplugin.publish)

    // Workaround for version catalog working inside precompiled scripts
    // Issue - https://github.com/gradle/gradle/issues/15383
    implementation(files(libs.javaClass.superclass.protectionDomain.codeSource.location))
}
