plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    id("kotlin-kapt")
    id("com.google.dagger.hilt.android")
    id("kotlin-parcelize")
}

android {
    namespace = "com.example.sportik"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.sportik"
        minSdk = 28
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        viewBinding = true
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.0"
    }
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.androidx.lifecycle.livedata.ktx)
    implementation(libs.androidx.lifecycle.viewmodel.ktx)
    //Navigation
    implementation(libs.androidx.navigation.fragment.ktx)
    implementation(libs.androidx.navigation.ui.ktx)
    //Room
    kapt(libs.room.compiler)
    implementation(libs.androidx.room.common)
    implementation(libs.androidx.room.ktx)
    implementation(libs.androidx.datastore.core.android)
    //Tests
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    //Glide
    implementation(libs.glide)
    annotationProcessor(libs.compiler)
    //noinspection KaptUsageInsteadOfKsp
    kapt(libs.compiler)
    //Retrofit, Gson
    implementation(libs.gson)
    implementation(libs.retrofit)
    implementation(libs.converter.gson)
    //RV
    implementation(libs.androidx.recyclerview)
    implementation(libs.androidx.recyclerview.selection)
    //Hilt
    implementation(libs.hilt.android)
    kapt(libs.hilt.android.compiler)
    //Coroutines
    implementation(libs.kotlinx.coroutines.android)
    //Compose
    val composeBom = platform("androidx.compose:compose-bom:2024.09.00")
    implementation(composeBom)
    androidTestImplementation(composeBom)
    implementation(libs.androidx.material3)
    implementation(libs.ui)
    implementation(libs.androidx.adaptive)
    implementation(libs.androidx.ui.tooling.preview)
    debugImplementation(libs.androidx.ui.tooling)
    implementation(libs.androidx.constraintlayout.compose)
    implementation(libs.library)
    implementation(libs.androidx.runtime.livedata)
    //Coil
    implementation(libs.coil)
    implementation(libs.coil.compose)
    implementation(libs.coil.svg)

}
kapt {
    correctErrorTypes = true
}