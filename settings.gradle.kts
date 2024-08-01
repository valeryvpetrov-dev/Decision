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

include(":composeApp")
include(":server")

include(":feature:make-decision:api")
include(":feature:make-decision:impl")
include(":feature:make-decision:presentation")
include(":feature:make-decision:di")
include(":feature:make-decision:ui:compose")

include(":feature:problem:api")
include(":feature:problem:impl")
include(":feature:problem:presentation")
include(":feature:problem:di")
include(":feature:problem:ui:compose")

include(":feature:solution:api")
include(":feature:solution:impl")
include(":feature:solution:presentation")
include(":feature:solution:di")
include(":feature:solution:ui:compose")

include(":feature:decision:api")
include(":feature:decision:impl")
include(":feature:decision:presentation")
include(":feature:decision:di")
include(":feature:decision:ui:compose")

include(":base:api")
include(":base:presentation")
include(":base:di")
include(":base:impl")

include(":umbrella:di")