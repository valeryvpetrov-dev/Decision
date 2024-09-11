plugins {
    alias(libs.plugins.decision.common.kmp)
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation(projects.feature.tabs.di)
            implementation(projects.feature.tabs.presentation)
            implementation(projects.feature.chatGpt.di)
            implementation(libs.koin.core)
            implementation(libs.decompose)
            implementation(libs.mvikotlin)
            implementation(libs.mvikotlin.main)
            implementation(libs.mvikotlin.logging)
        }
    }
}

android {
    namespace = "dev.valeryvpetrov.decision.umbrella.di"
}