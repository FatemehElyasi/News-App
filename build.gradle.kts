// Top-level build file where you can add configuration options common to all sub-projects/modules.
configurations.all {
    resolutionStrategy {
        force("org.jetbrains:annotations:23.0.0")
    }
}

plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.kotlin.compose) apply false
    alias(libs.plugins.kotlin.serializable) apply false
    alias(libs.plugins.kspPlugin) apply false
}