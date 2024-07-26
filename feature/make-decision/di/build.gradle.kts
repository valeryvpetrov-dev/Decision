import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.kotlin.serialization)
}

kotlin {

    iosX64()
    iosArm64()
    iosSimulatorArm64()

    androidTarget {
        @OptIn(ExperimentalKotlinGradlePluginApi::class)
        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_11)
        }
    }

    jvm()

    sourceSets {
        commonMain.dependencies {
            implementation(projects.base.di)
            implementation(projects.feature.makeDecision.api)
            implementation(projects.feature.problem.api)
            implementation(projects.feature.solution.api)
            implementation(projects.feature.decision.api)
            implementation(projects.feature.makeDecision.presentation)
            implementation(projects.feature.problem.presentation)
            implementation(projects.feature.solution.presentation)
            implementation(projects.feature.decision.presentation)
            implementation(projects.feature.makeDecision.data)
            implementation(projects.feature.problem.di)
            implementation(projects.feature.solution.di)
            implementation(projects.feature.decision.di)
            implementation(libs.mvikotlin)
            implementation(libs.mvikotlin.main)
            implementation(libs.mvikotlin.extensions.coroutines)
            implementation(libs.mvikotlin.timetravel)
            implementation(libs.kotlinx.serialization.core)
            implementation(libs.kotlinx.coroutines.core)
            implementation(libs.decompose)
            implementation(libs.essenty.lifecycle)
            implementation(libs.koin.core)
        }
    }
}

android {
    namespace = "dev.valeryvpetrov.decision.feature.make_decision.di"
    compileSdk = libs.versions.android.compileSdk.get().toInt()
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    defaultConfig {
        minSdk = libs.versions.android.minSdk.get().toInt()
    }
}
