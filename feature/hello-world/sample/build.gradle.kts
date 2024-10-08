import org.jetbrains.compose.desktop.application.dsl.TargetFormat
import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.kotlin.multiplatform)
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.compose)
    alias(libs.plugins.compose.compiler)
}

kotlin {
    androidTarget {
        @OptIn(ExperimentalKotlinGradlePluginApi::class)
        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_11)
        }
    }

    jvm()

    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64(),
    ).takeIf {
        // Export the framework only for Xcode builds
        "XCODE_VERSION_MAJOR" in System.getenv().keys
    }?.forEach {
        it.binaries.framework {
            baseName = "Shared"
            isStatic = true

            export(projects.feature.helloWorld.presentation)

            export(libs.decompose)
            export(libs.essenty.lifecycle)
        }
    }

    sourceSets {
        androidMain.dependencies {
            implementation(compose.preview)
            implementation(libs.androidx.activity.compose)
            implementation(libs.koin.android)
        }
        commonMain.dependencies {
            // Use api for exported dependencies in ios
            api(libs.decompose)
            api(libs.essenty.lifecycle)

            implementation(projects.feature.helloWorld.presentation)
            implementation(projects.feature.helloWorld.ui.compose)
            implementation(projects.feature.helloWorld.di)

            implementation(compose.runtime)
            implementation(compose.foundation)
            implementation(compose.material)
            implementation(compose.ui)
            implementation(compose.components.resources)
            implementation(compose.components.uiToolingPreview)

            implementation(libs.mvikotlin.timetravel)
            implementation(libs.decompose.extensions.compose)
            implementation(libs.koin.core)
            implementation(libs.koin.jetbrains.compose)
        }
        jvmMain.dependencies {
            implementation(compose.desktop.currentOs)

            implementation(libs.mvikotlin)
        }
    }
}

android {
    namespace = "dev.valeryvpetrov.decision.feature.hello_world.sample"
    compileSdk = libs.versions.android.compileSdk.get().toInt()

    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
    sourceSets["main"].res.srcDirs("src/androidMain/res")
    sourceSets["main"].resources.srcDirs("src/commonMain/resources")

    defaultConfig {
        applicationId = "dev.valeryvpetrov.decision.feature.hello_world.sample"
        minSdk = libs.versions.android.minSdk.get().toInt()
        targetSdk = libs.versions.android.targetSdk.get().toInt()
        versionCode = 1
        versionName = "1.0"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    buildFeatures {
        compose = true
    }
    dependencies {
        debugImplementation(compose.uiTooling)
    }
}

compose.desktop {
    application {
        mainClass = "MainKt"

        nativeDistributions {
            targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)
            packageName = "dev.valeryvpetrov.decision.feature.hello_world.sample"
            packageVersion = "1.0.0"
        }
    }
}