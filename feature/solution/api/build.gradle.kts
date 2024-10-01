plugins {
    alias(libs.plugins.build.logic.feature.api)
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
