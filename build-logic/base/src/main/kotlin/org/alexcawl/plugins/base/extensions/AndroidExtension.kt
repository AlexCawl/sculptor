package org.alexcawl.plugins.base.extensions

import com.android.build.api.dsl.AndroidResources
import com.android.build.api.dsl.ApplicationExtension
import com.android.build.api.dsl.BuildFeatures
import com.android.build.api.dsl.BuildType
import com.android.build.api.dsl.CommonExtension
import com.android.build.api.dsl.DefaultConfig
import com.android.build.api.dsl.Installation
import com.android.build.api.dsl.LibraryExtension
import com.android.build.api.dsl.ProductFlavor
import org.gradle.api.Project
import org.gradle.kotlin.dsl.findByType

internal typealias AndroidExtensions = CommonExtension<
        out BuildFeatures,
        out BuildType,
        out DefaultConfig,
        out ProductFlavor,
        out AndroidResources,
        out Installation>

@PublishedApi
internal val Project.androidExtension: AndroidExtensions
    get() = extensions.findByType(ApplicationExtension::class)
        ?: extensions.findByType(LibraryExtension::class)
        ?: error(
            """
                "Project.androidExtension" value may be called only from android application 
                or android library gradle script!
            """.trimIndent()
        )
