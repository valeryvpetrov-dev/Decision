plugins {
    alias(libs.plugins.build.logic.feature.impl)
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation(projects.feature.makeDecision.api)
            implementation(projects.feature.problem.api)
            implementation(projects.feature.solution.api)
            implementation(projects.feature.history.api)
        }
    }
}

android {
    namespace = "dev.valeryvpetrov.decision.feature.make_decision.impl"
}