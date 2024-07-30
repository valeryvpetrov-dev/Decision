import ext.projects
import ext.versionCatalog
import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi
import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension
import com.android.build.gradle.LibraryExtension as AndroidLibraryExtension

with(plugins) {
    apply(project.versionCatalog.plugins.kotlinMultiplatform.get().pluginId)
    apply(project.versionCatalog.plugins.androidLibrary.get().pluginId)
    apply(project.versionCatalog.plugins.kotlinSerialization.get().pluginId)
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
    jvm()

    sourceSets {
        commonMain.dependencies {
            api(projects.base.di)
            implementation(versionCatalog.libs.mvikotlin)
            implementation(versionCatalog.libs.mvikotlinMain)
            implementation(versionCatalog.libs.mvikotlinExtensionsCoroutines)
            implementation(versionCatalog.libs.mvikotlinTimetravel)
            implementation(versionCatalog.libs.kotlinxSerializationCore)
            implementation(versionCatalog.libs.kotlinxCoroutinesCore)
            implementation(versionCatalog.libs.decompose)
            implementation(versionCatalog.libs.essentyLifecycle)
            implementation(versionCatalog.libs.koinCore)
        }
    }
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
