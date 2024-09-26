plugins {
    alias(libs.plugins.decision.feature.di)
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation(projects.feature.tabs.presentation)

            implementation(projects.feature.makeDecision.di)
            implementation(projects.feature.makeDecision.presentation)

            implementation(projects.feature.history.di)
            implementation(projects.feature.history.presentation)
        }
    }
}

android {
    namespace = "dev.valeryvpetrov.decision.feature.tabs.di"
}