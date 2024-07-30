plugins {
    alias(libs.plugins.decision.feature.data)
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation(projects.feature.makeDecision.api)
            implementation(projects.feature.problem.api)
            implementation(projects.feature.solution.api)
        }
    }
}