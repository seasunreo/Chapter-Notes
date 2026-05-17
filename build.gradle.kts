// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.android.application) apply false
    // Make sure the Google services plugin version is correct
    id("com.google.gms.google-services") version "4.4.2" apply false
}
