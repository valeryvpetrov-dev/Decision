plugins {
    alias(libs.plugins.build.logic.feature.di)
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation(projects.feature.helloWorld.impl)
            implementation(projects.feature.helloWorld.presentation)
        }
    }
}

android {
    namespace = "dev.valeryvpetrov.decision.feature.hello_world.di"
}
