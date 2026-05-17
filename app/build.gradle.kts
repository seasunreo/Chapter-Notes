plugins {
    id("com.android.application")
    id("com.google.gms.google-services") // Google services plugin
}

android {
    namespace = "com.example.finals_project"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.finals_project"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        // Add Firebase Realtime Database URL as a BuildConfig field
        buildConfigField(
            "String",
            "FIREBASE_DATABASE_URL",
            "\"https://final-project-a84e7f-default-rtdb.asia-southeast1.firebasedatabase.app/\""
        )
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    // Enable BuildConfig to use buildConfigField
    buildFeatures {
        buildConfig = true
        viewBinding = true // Enable View Binding
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}

dependencies {
    // AndroidX Libraries
    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)

    // Testing Libraries
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)

    // Firebase BoM (Bill of Materials)
    implementation(platform(libs.firebase.bom))

    // Firebase Libraries
    implementation(libs.firebase.analytics)   // Firebase Analytics
    implementation(libs.firebase.database)    // Firebase Realtime Database
    implementation(libs.firebase.auth)        // Firebase Authentication
}
