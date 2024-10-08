plugins {
    alias(libs.plugins.build.logic.feature.presentation)
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation(projects.feature.helloWorld.api)
        }
    }
}

android {
    namespace = "dev.valeryvpetrov.decision.feature.hello_world.presentation"
}
