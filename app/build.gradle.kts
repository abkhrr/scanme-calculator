@file:Suppress("UnstableApiUsage")

import java.util.Locale

plugins {
    id ("com.android.application")
    id ("kotlin-android")
    id ("kotlin-parcelize")
    id ("org.jetbrains.kotlin.android")
    id ("kotlin-kapt")
    id ("androidx.navigation.safeargs.kotlin")
    id ("dagger.hilt.android.plugin")
}

android {
    namespace = "com.abkhrr.scanme"
    compileSdk = Versions.compileSdk

    defaultConfig {
        applicationId = "com.abkhrr.scanme"
        minSdk = Versions.minSdk
        targetSdk = Versions.targetSdk
        versionCode = Versions.versionCode
        versionName = Versions.versionName

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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

    packaging.jniLibs.excludes.add("META-INF/LICENSE.md")
    buildFeatures.dataBinding = true
    buildFeatures.viewBinding = true
    buildFeatures.buildConfig = true

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

android.applicationVariants.all {
    val variant = this
    val flavorName = variant.flavorName?.replaceFirstChar {
        if (it.isLowerCase()) it.titlecase(
            Locale.getDefault()
        ) else it.toString()
    }
    variant.outputs.all {
        val output = this
        output.outputFile.name.replace("app-release", "app-${flavorName}-${variant.name}")
        output.outputFile.name.replace("app-debug", "app-${flavorName}-${variant.name}")
    }

    variant.buildConfigField("String", "FLAVOR_NAME", "\"$flavorName\"")
}

androidComponents {
    onVariants { variant ->
        val applicationId = when(variant.flavorName) {
            "greenFilesystem" -> "com.abkhrr.greenfilesystem"
            "redFilesystem" -> "com.abkhrr.scanme.redfilesystem"
            "redBuiltInCamera" -> "com.abkhrr.scanme.redbuiltincamera"
            "greenBuiltInCamera" -> "com.abkhrr.scanme.greenbuiltincamera"
            else -> throw(IllegalStateException("Whats your flavor? ${variant.flavorName}!"))
        }

        variant.applicationId.set(applicationId)
    }
}

dependencies {
    // libs
    implementation(project(":common"))
    implementation(project(":libs"))
    implementation(project(":feature:builtInCamera"))
    implementation(project(":feature:result"))

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

    // Kotlin - Coroutines
    implementation(Dependencies.kotlinStdLib)
    implementation(Dependencies.kotlinExtensionsRuntime)
    implementation(Dependencies.coroutines)
    implementation(Dependencies.coroutinesCore)

    // Dagger Hilt
    implementation(Dependencies.hilt)
    implementation("androidx.test.uiautomator:uiautomator:2.2.0")
    kapt(Dependencies.hiltCompiler)

    // Glide - Image
    implementation(Dependencies.glide)
    kapt(Dependencies.glideCompiler)

    // Google (ML-KIT, GSON)
    implementation(Dependencies.mlKitTextRecognition)
    implementation(Dependencies.gson)

    // CameraX
    implementation(Dependencies.camera2)
    implementation(Dependencies.cameraLifecycle)
    implementation(Dependencies.cameraView)

    // Test
    testImplementation(Dependencies.jUnit)
    androidTestImplementation(Dependencies.jUnitExt)
    androidTestImplementation(Dependencies.espressoCore)
    testImplementation(Dependencies.ioMockk)
    androidTestImplementation(Dependencies.ioMockkAndroid)
    testImplementation(Dependencies.hiltTest)
    androidTestImplementation(Dependencies.androidxTestRules)
}