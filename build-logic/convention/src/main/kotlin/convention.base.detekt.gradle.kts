import io.gitlab.arturbosch.detekt.Detekt
import io.gitlab.arturbosch.detekt.report.ReportMergeTask
import org.alexcawl.plugins.detekt.DetektConfiguration
import org.alexcawl.plugins.detekt.detektConfiguration
import org.alexcawl.plugins.detektVersionValue
import org.alexcawl.plugins.javaCompileTargetVersionValue

plugins {

}

detektConfiguration { configuration: DetektConfiguration ->
    with(configuration) {
        // Version of detekt that will be used. When unspecified the latest detekt
        // version found will be used. Override to stay on the same version.
        toolVersion = detektVersionValue

        // The directories where detekt looks for source files.
        // Defaults to `files("src/main/java", "src/test/java", "src/main/kotlin", "src/test/kotlin")`.
        source.setFrom(
            "src/main/kotlin", "src/test/kotlin",
            "src/commonMain/kotlin", "src/commonTest/kotlin",
            "src/androidMain/kotlin", "src/androidTest/kotlin",
            "src/jvmMain/kotlin", "src/jvmTest/kotlin",
            "src/iosMain/kotlin", "src/iosTest/kotlin",
        )

        // Builds the AST in parallel. Rules are always executed in parallel.
        // Can lead to speedups in larger projects. `false` by default.
        parallel = true

        // Define the detekt configuration(s) you want to use.
        // Defaults to the default detekt configuration.
        config.setFrom(detektConfigPathValue)

        // Applies the config files on top of detekt's default config file. `false` by default.
        buildUponDefaultConfig = false

        // Turns on all the rules. `false` by default.
        allRules = false

        // Disables all default detekt rulesets and will only run detekt with custom rules
        // defined in plugins passed in with `detektPlugins` configuration. `false` by default.
        disableDefaultRuleSets = false

        // Adds debug output during task execution. `false` by default.
        debug = false

        // If set to `true` the build does not fail when the
        // maxIssues count was reached. Defaults to `false`.
        ignoreFailures = false

        // Specify the base path for file paths in the formatted reports.
        // If not set, all file paths reported will be absolute file path.
        basePath = projectDir.absolutePath

        autoCorrect = true
    }
}


val reportMerge by tasks.registering(ReportMergeTask::class) {
    output.set(rootProject.layout.buildDirectory.file("reports/detekt/merge.xml")) // or "reports/detekt/merge.sarif"
}

tasks.withType<Detekt>().configureEach {
    jvmTarget = javaCompileTargetVersionValue

    reports {
        // Enable/Disable XML report (default: true)
        xml.required.set(true)
        xml.outputLocation.set(file("build/reports/detekt.xml"))

        // Enable/Disable HTML report (default: true)
        html.required.set(false)

        // Enable/Disable TXT report (default: true)
        txt.required.set(false)

        // Enable/Disable SARIF report (default: false)
        sarif.required.set(false)

        // Enable/Disable MD report (default: false)
        md.required.set(false)
    }

    finalizedBy(reportMerge)
}

reportMerge {
    input.from(tasks.withType<Detekt>().map { it.xmlReportFile }) // or .sarifReportFile
}
