plugins {
    alias(libs.plugins.decision.feature.presentation)
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation(projects.feature.problem.api)
        }
    }
}

android {
    namespace = "dev.valeryvpetrov.decision.feature.problem.presentation"
}
