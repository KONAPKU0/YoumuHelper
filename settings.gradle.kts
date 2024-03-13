pluginManagement {
    repositories {
        google()
        maven { setUrl("https://jitpack.io") }
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        maven { setUrl("https://jitpack.io") }
        mavenCentral()
        mavenLocal()
    }
}

rootProject.name = "YoumuHelper"
include(":app")
include(":helper")
