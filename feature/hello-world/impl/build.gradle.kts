plugins {
    alias(libs.plugins.build.logic.feature.impl)
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            api(projects.feature.helloWorld.api)
        }
    }
}

android {
    namespace = "dev.valeryvpetrov.decision.feature.hello_world.impl"
}
