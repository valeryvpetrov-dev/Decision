import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.kotlin.multiplatform)
    alias(libs.plugins.android.library)
}

kotlin {
    androidTarget {
        @OptIn(ExperimentalKotlinGradlePluginApi::class)
        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_11)
        }
    }

    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64(),
    ).takeIf {
        // Export the framework only for Xcode builds
        "XCODE_VERSION_MAJOR" in System.getenv().keys
    }?.forEach {
        it.binaries.framework {
            baseName = "UmbrellaIos"
            isStatic = true

            export(projects.feature.makeDecision.api)
            export(projects.feature.problem.api)
            export(projects.feature.solution.api)
            export(projects.feature.decision.api)

            export(projects.feature.makeDecision.presentation)
            export(projects.feature.problem.presentation)
            export(projects.feature.solution.presentation)
            export(projects.feature.decision.presentation)

            export(projects.umbrella.di)
            export(libs.decompose)
            export(libs.essenty.lifecycle)

            export(libs.kotlinx.coroutines.core)
        }
    }

    sourceSets {
        commonMain.dependencies {
            // Use api for exported dependencies in ios
            api(projects.feature.makeDecision.api)
            api(projects.feature.problem.api)
            api(projects.feature.solution.api)
            api(projects.feature.decision.api)

            api(projects.feature.makeDecision.presentation)
            api(projects.feature.problem.presentation)
            api(projects.feature.solution.presentation)
            api(projects.feature.decision.presentation)

            api(projects.umbrella.di)
            api(libs.decompose)
            api(libs.essenty.lifecycle)

            api(libs.kotlinx.coroutines.core)
        }
    }
}

android {
    namespace = "dev.valeryvpetrov.decision.umbrella.ios"
    compileSdk = libs.versions.android.compileSdk.get().toInt()
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    defaultConfig {
        minSdk = libs.versions.android.minSdk.get().toInt()
    }
}