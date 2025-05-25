package org.alexcawl.plugins.base.configurations

import org.gradle.api.Project
import org.gradle.api.publish.maven.MavenPom
import org.gradle.api.publish.maven.MavenPomDeveloper
import org.gradle.api.publish.maven.MavenPomLicense
import org.gradle.api.publish.maven.MavenPomScm

public data class PublishConfiguration(
    public var projectGroup: String? = null,
    public var projectName: String? = null,
    public var projectVersion: String? = null,
    public var projectDescription: String? = null,
    public var projectUrl: String? = null,

    public var license: String? = null,
    public var licenseUrl: String? = null,

    public var developerId: String? = null,
    public var developerName: String? = null,
    public var developerEmail: String? = null,

    public var scmConnection: String? = null,
    public var scmDeveloperConnection: String? = null,
    public var scmUrl: String? = null
)

private val Project.fullName: String get() {
    val names = ArrayList<String>()
    names.add(name)

    var currentProject = this
    while (currentProject.parent != null && currentProject.parent != rootProject) {
        val parentProject = currentProject.parent!!
        names.add(parentProject.name)
        currentProject = parentProject
    }

    return names.reversed().joinToString("-")
}

private fun MavenPom.configure() {
    name.set("klib-template")
    description.set("klib-template for Kotlin Multiplatform")
    url.set("https://github.com/tamimattafi/klib-template")

    licenses {
        license(MavenPomLicense::configure)
    }

    developers {
        developer(MavenPomDeveloper::configure)
    }

    scm(MavenPomScm::configure)
}

private fun MavenPomLicense.configure() {
    name.set("Apache License 2.0")
    url.set("https://github.com/tamimattafi/klib-template/blob/main/LICENSE")
}

private fun MavenPomDeveloper.configure() {
    id.set("attafitamim")
    name.set("Tamim Attafi")
    email.set("attafitamim@gmail.com")
}

private fun MavenPomScm.configure() {
    connection.set("scm:git:github.com/tamimattafi/klib-template.git")
    developerConnection.set("scm:git:ssh://github.com/tamimattafi/klib-template.git")
    url.set("https://github.com/tamimattafi/klib-template/tree/main")
}
