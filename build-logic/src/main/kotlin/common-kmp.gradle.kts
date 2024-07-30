import ext.versionCatalog
import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi
import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension
import com.android.build.gradle.LibraryExtension as AndroidLibraryExtension

with(plugins) {
    apply(project.versionCatalog.plugins.kotlinMultiplatform.get().pluginId)
    apply(project.versionCatalog.plugins.androidLibrary.get().pluginId)
}

extensions.configure<KotlinMultiplatformExtension> {
    androidTarget {
        @OptIn(ExperimentalKotlinGradlePluginApi::class)
        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_11)
        }
    }

    iosX64()
    iosArm64()
    iosSimulatorArm64()
    jvm("desktop")
}

extensions.configure<AndroidLibraryExtension> {
    compileSdk = versionCatalog.versions.androidCompileSdk
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    defaultConfig {
        minSdk = versionCatalog.versions.androidMinSdk
    }
}
