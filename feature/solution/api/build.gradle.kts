plugins {
    alias(libs.plugins.decision.feature.api)
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation(projects.feature.problem.api)
        }
    }
}

android {
    namespace = "dev.valeryvpetrov.decision.feature.solution.api"
}
