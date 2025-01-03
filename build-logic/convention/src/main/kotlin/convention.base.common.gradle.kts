import org.alexcawl.plugins.javaCompileTargetVersionValue
import org.alexcawl.plugins.jvm.javaCompilerConfiguration
import org.alexcawl.plugins.jvm.kotlinCompilerConfiguration
import org.alexcawl.plugins.kotlinLanguageVersionValue
import org.alexcawl.plugins.toJvmTarget
import org.alexcawl.plugins.toKotlinVersion

kotlinCompilerConfiguration {
    jvmTarget.set(javaCompileTargetVersionValue.toJvmTarget())
    languageVersion.set(kotlinLanguageVersionValue.toKotlinVersion())
}

javaCompilerConfiguration {
    sourceCompatibility = javaCompileTargetVersionValue
    targetCompatibility = javaCompileTargetVersionValue
}
