rootProject.name = "Decision"
enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

pluginManagement {
    includeBuild("build-logic")
    repositories {
        google {
            mavenContent {
                includeGroupAndSubgroups("androidx")
                includeGroupAndSubgroups("com.android")
                includeGroupAndSubgroups("com.google")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}

dependencyResolutionManagement {
    repositories {
        google {
            mavenContent {
                includeGroupAndSubgroups("androidx")
                includeGroupAndSubgroups("com.android")
                includeGroupAndSubgroups("com.google")
            }
        }
        mavenCentral()
    }
}

include(
    ":composeApp",
    ":server",

    ":feature:make-decision:api",
    ":feature:make-decision:impl",
    ":feature:make-decision:presentation",
    ":feature:make-decision:di",
    ":feature:make-decision:ui:compose",

    ":feature:problem:api",
    ":feature:problem:impl",
    ":feature:problem:presentation",
    ":feature:problem:di",
    ":feature:problem:ui:compose",

    ":feature:solution:api",
    ":feature:solution:impl",
    ":feature:solution:presentation",
    ":feature:solution:di",
    ":feature:solution:ui:compose",

    ":feature:decision:api",
    ":feature:decision:impl",
    ":feature:decision:presentation",
    ":feature:decision:di",
    ":feature:decision:ui:compose",

    ":feature:chat-gpt:api",
    ":feature:chat-gpt:impl",
    ":feature:chat-gpt:di",

    ":feature:tabs:di",
    ":feature:tabs:presentation",
    ":feature:tabs:ui:compose",

    ":feature:history:api",
    ":feature:history:impl",
    ":feature:history:di",
    ":feature:history:presentation",
    ":feature:history:ui:compose",

    ":base:api",
    ":base:presentation",
    ":base:di",
    ":base:impl",

    ":umbrella:di",
    ":umbrella:ios"
)