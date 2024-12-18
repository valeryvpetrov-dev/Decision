plugins {
    alias(libs.plugins.build.logic.feature.presentation)
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation(projects.feature.makeDecision.presentation)
            implementation(projects.feature.history.presentation)
        }
    }
}

android {
    namespace = "dev.valeryvpetrov.decision.feature.tabs.presentation"
}