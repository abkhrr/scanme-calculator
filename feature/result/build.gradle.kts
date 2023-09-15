@file:Suppress("UnstableApiUsage")

plugins {
    id ("com.android.library")
    id ("org.jetbrains.kotlin.android")
    id ("kotlin-parcelize")
    id ("kotlin-android")
    id ("kotlin-kapt")
    id ("androidx.navigation.safeargs.kotlin")
    id ("dagger.hilt.android.plugin")
}

android {
    namespace = "com.abkhrr.result"
    compileSdk = Versions.compileSdk

    defaultConfig {
        minSdk = Versions.minSdk

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("project-proguard-rules.pro")
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    kotlinOptions {
        jvmTarget = "17"
    }

    buildFeatures.dataBinding = true
    buildFeatures.viewBinding = true

    flavorDimensions += "theme"
    flavorDimensions += "uiFunctionality"

    productFlavors {
        create("red") {
            dimension = "theme"
        }
        create("green") {
            dimension = "theme"
        }
        create("filesystem") {
            dimension = "uiFunctionality"
        }
        create("builtInCamera") {
            dimension = "uiFunctionality"
        }
    }
}

dependencies {
    // Libs
    implementation(project(":libs"))
    implementation(project(":common"))

    // Common
    implementation(Dependencies.navigationFragment)
    implementation(Dependencies.navigationUi)
    implementation(Dependencies.coreKtx)
    implementation(Dependencies.appCompat)
    implementation(Dependencies.androidMaterial)
    implementation(Dependencies.constraintLayout)
    implementation(Dependencies.cardView)
    implementation(Dependencies.activityKtx)

    // Lifecycle
    implementation(Dependencies.lifecycleExtension)
    implementation(Dependencies.lifecycleViewModelKtx)
    implementation(Dependencies.lifecycleViewModelSavedState)
    implementation(Dependencies.lifecycleReactiveStreamsKtx)
    implementation(Dependencies.lifecycleLiveDataKtx)
    implementation(Dependencies.lifecycleProcess)
    implementation(Dependencies.lifecycleRuntimeKtx)
    implementation(Dependencies.lifecycleCommonJava8)

    // Google (Firebase, ML-KIT, GSON)
    implementation(Dependencies.mlKitTextRecognition)

    // Glide - Image
    implementation(Dependencies.glide)
    kapt(Dependencies.glideCompiler)

    // Test
    testImplementation(Dependencies.jUnit)
    androidTestImplementation(Dependencies.jUnitExt)
    androidTestImplementation(Dependencies.espressoCore)
    testImplementation(Dependencies.ioMockk)
    androidTestImplementation(Dependencies.ioMockkAndroid)
    testImplementation(Dependencies.hiltTest)

    // Dagger Hilt
    implementation(Dependencies.hilt)
    kapt(Dependencies.hiltCompiler)

    // Kotlin - Coroutines
    implementation(Dependencies.kotlinStdLib)
    implementation(Dependencies.kotlinExtensionsRuntime)
    implementation(Dependencies.coroutines)
    implementation(Dependencies.coroutinesCore)
}