plugins {
    alias(libs.plugins.build.logic.feature.ui.compose)
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation(projects.feature.history.api)
            implementation(projects.feature.history.presentation)
        }
    }
}

android {
    namespace = "dev.valeryvpetrov.decision.feature.history.ui.compose"
}