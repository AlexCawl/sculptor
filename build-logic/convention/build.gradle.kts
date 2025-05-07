plugins {
    `kotlin-dsl`
}

dependencies {
    implementation(projects.base)

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

    // Workaround for version catalog working inside precompiled scripts
    // Issue - https://github.com/gradle/gradle/issues/15383
    implementation(files(libs.javaClass.superclass.protectionDomain.codeSource.location))
}

//gradlePlugin {
//    plugins {
//        register("convention.project.kotlin.library") {
//            id = "convention.project.kotlin.library"
//            implementationClass = "org.alexcawl.plugins.project.KmpModulePlugin"
//        }
//        register("convention.project.compose.library") {
//            id = "convention.project.compose.library"
//            implementationClass = "org.alexcawl.plugins.project.CmpModulePlugin"
//        }
//        register("convention.project.showroom.application") {
//            id = "convention.project.showroom.application"
//            implementationClass = "org.alexcawl.plugins.project.ShowroomModulePlugin"
//        }
//    }
//}