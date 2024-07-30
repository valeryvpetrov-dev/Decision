plugins {
    alias(libs.plugins.decision.feature.di)
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation(projects.feature.problem.api)
            implementation(projects.feature.problem.presentation)
        }
    }
}

android {
    namespace = "dev.valeryvpetrov.decision.feature.problem.di"
}
