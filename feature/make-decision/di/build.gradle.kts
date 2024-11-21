plugins {
    alias(libs.plugins.build.logic.feature.di)
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation(projects.feature.makeDecision.api)
            implementation(projects.feature.problem.api)
            implementation(projects.feature.solution.api)
            implementation(projects.feature.decision.api)
            implementation(projects.feature.history.api)

            implementation(projects.feature.makeDecision.impl)

            implementation(projects.feature.makeDecision.presentation)
            implementation(projects.feature.problem.presentation)
            implementation(projects.feature.solution.presentation)
            implementation(projects.feature.decision.presentation)

            implementation(projects.feature.problem.di)
            implementation(projects.feature.solution.di)
            implementation(projects.feature.decision.di)
        }
    }
}

android {
    namespace = "dev.valeryvpetrov.decision.feature.make_decision.di"
}