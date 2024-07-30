plugins {
    alias(libs.plugins.decision.feature.di)
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
    namespace = "dev.valeryvpetrov.decision.feature.solution.di"
}
