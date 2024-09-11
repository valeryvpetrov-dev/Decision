plugins {
    alias(libs.plugins.decision.feature.ui.compose)
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation(projects.feature.tabs.presentation)
            implementation(projects.feature.makeDecision.presentation)
            implementation(projects.feature.makeDecision.ui.compose)
            implementation(libs.decompose.extensions.compose)
        }
    }
}

android {
    namespace = "dev.valeryvpetrov.decision.feature.tabs.ui.compose"
}