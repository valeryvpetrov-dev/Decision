plugins {
    alias(libs.plugins.decision.feature.presentation)
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation(projects.feature.makeDecision.presentation)
        }
    }
}

android {
    namespace = "dev.valeryvpetrov.decision.feature.tabs.presentation"
}