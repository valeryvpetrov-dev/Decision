plugins {
    alias(libs.plugins.build.logic.common.kmp)
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation(projects.feature.tabs.di)
            implementation(projects.feature.tabs.presentation)
            implementation(projects.feature.chatGpt.di)
            implementation(libs.koin.core)
            implementation(libs.decompose)
            implementation(libs.bundles.mvikotlin)
        }
    }
}

android {
    namespace = "dev.valeryvpetrov.decision.umbrella.di"
}