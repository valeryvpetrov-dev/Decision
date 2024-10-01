plugins {
    alias(libs.plugins.build.logic.feature.ui.compose)
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation(projects.feature.problem.presentation)
        }
    }
}

android {
    namespace = "dev.valeryvpetrov.decision.feature.problem.ui.compose"
}