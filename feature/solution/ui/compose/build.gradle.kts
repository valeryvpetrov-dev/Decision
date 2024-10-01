plugins {
    alias(libs.plugins.build.logic.feature.ui.compose)
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation(projects.feature.solution.api)
            implementation(projects.feature.solution.presentation)
        }
    }
}

android {
    namespace = "dev.valeryvpetrov.decision.feature.solution.ui.compose"
}