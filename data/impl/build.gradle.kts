plugins {
    alias(libs.plugins.kotlinMultiplatform)
}

kotlin {

    iosX64()
    iosArm64()
    iosSimulatorArm64()
    jvm()

    sourceSets {
        commonMain.dependencies {
            implementation(projects.domain)
            implementation(projects.data.api)
            implementation(libs.kotlinx.coroutines.core)
        }
    }
}