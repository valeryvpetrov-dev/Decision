plugins {
    alias(libs.plugins.build.logic.feature.ui.compose)
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation(projects.feature.tabs.presentation)
            implementation(projects.feature.makeDecision.presentation)
            implementation(projects.feature.makeDecision.ui.compose)
            implementation(projects.feature.history.presentation)
            implementation(projects.feature.history.ui.compose)
            implementation(libs.decompose.extensions.compose)
        }
    }
}

android {
    namespace = "dev.valeryvpetrov.decision.feature.tabs.ui.compose"
}