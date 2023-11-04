pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "TestHammers"
include(":app")
include(":core:common")
include(":feature_main:presentation")
include(":feature_main:domain")
include(":feature_main:data")
include(":core:network")
include(":core:database")
include(":feature_main:api")
include(":core:common_compose")
