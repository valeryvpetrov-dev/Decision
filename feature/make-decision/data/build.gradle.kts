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
            implementation(projects.feature.makeDecision.api)
            implementation(projects.feature.problem.api)
            implementation(projects.feature.solution.api)
            implementation(libs.kotlinx.coroutines.core)
        }
    }
}