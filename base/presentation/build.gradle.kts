plugins {
    alias(libs.plugins.decision.common.kmp)
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation(projects.base.api)
            implementation(libs.kotlinx.coroutines.core)
            api(libs.decompose)
            api(libs.mvikotlin)
            api(libs.mvikotlin.main)
            api(libs.mvikotlin.extensions.coroutines)
        }
    }
}

android {
    namespace = "dev.valeryvpetrov.decision.base.presentation"
}
