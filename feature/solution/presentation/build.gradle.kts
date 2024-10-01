plugins {
    alias(libs.plugins.build.logic.feature.presentation)
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation(projects.feature.solution.api)
        }
    }
}

android {
    namespace = "dev.valeryvpetrov.decision.feature.solution.presentation"
}