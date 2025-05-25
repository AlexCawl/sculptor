package org.alexcawl.plugins.base.extensions

import com.vanniktech.maven.publish.MavenPublishBaseExtension
import org.gradle.api.Project

@PublishedApi
internal val Project.publishExtension: MavenPublishBaseExtension
    get() = extensions.getByName(/* name = */ "mavenPublishing")
        as MavenPublishBaseExtension
