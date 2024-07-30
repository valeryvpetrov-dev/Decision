plugins {
    alias(libs.plugins.decision.feature.api)
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            api(projects.feature.problem.api)
            api(projects.feature.solution.api)
            api(projects.feature.decision.api)
        }
    }
}

android {
    namespace = "dev.valeryvpetrov.decision.feature.make_decision.api"
}
