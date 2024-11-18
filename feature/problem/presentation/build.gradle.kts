plugins {
    alias(libs.plugins.build.logic.feature.presentation)
    alias(libs.plugins.icerock.multiplatform.resources)
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

multiplatformResources {
    resourcesPackage.set("dev.valeryvpetrov.decision.feature.problem.presentation")
}