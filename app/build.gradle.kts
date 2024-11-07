plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    id("com.google.devtools.ksp")
}

android {
    namespace = "com.example.savr"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.savr"
        minSdk = 25
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        compose = true
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    implementation(libs.material3)
    implementation(libs.ui)
    implementation(libs.ui.tooling.preview) // For previews
    implementation(libs.landscapist.glide)
    implementation(libs.landscapist.coil)
    implementation(libs.landscapist.fresco)
    implementation(libs.coil.compose)
    implementation(libs.material)
    implementation(libs.androidx.foundation.layout.android)
    implementation(libs.androidx.room.common)
    implementation(libs.androidx.navigation.runtime.ktx)
    implementation(libs.androidx.navigation.compose)
    implementation(libs.androidx.foundation.layout.android)

    implementation(libs.hilt.android)
    implementation(libs.androidx.room.ktx)
    implementation(libs.firebase.firestore.ktx)
    ksp(libs.androidx.room.compiler)

    implementation(libs.ui.graphics) // Or your current version
    implementation(libs.androidx.ui.tooling.preview) // Or your current version
    implementation(libs.mpandroidchart) // Import MPAndroidChart

    testImplementation(libs.junit)

    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)

    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
}

