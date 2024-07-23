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
            implementation(projects.data.api)
            implementation(projects.data.impl)
            implementation(libs.koin.core)
        }
    }
}