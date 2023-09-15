object Versions {
    const val navArgs = "2.7.1"
    const val coreKtx = "1.10.1"
    const val appCompat = "1.6.1"
    const val material = "1.9.0"
    const val constraintLayout = "2.1.4"
    const val cardView = "1.0.0"
    const val activityKtx = "1.7.2"
    const val lifecycleArch = "1.1.1"
    const val lifecycle = "2.6.1"
    const val kotlin = "1.8.20"
    const val coroutines = "1.6.4"
    const val hilt = "2.44"
    const val glide = "4.14.2"
    const val firebaseBom = "32.2.3"
    const val mlKitTextRecognition = "16.0.0"
    const val gson = "2.9.0"
    const val cameraX = "1.3.0-rc01"
    const val jUnit = "4.13.2"
    const val jUnitExt = "1.1.5"
    const val espresso = "3.5.1"
    const val mockk = "1.13.7"
    const val hiltTest = "2.38.1"

    const val compileSdk = 34
    const val minSdk = 24
    const val targetSdk = 34
    const val versionCode = 1
    const val versionName = "1.0"
    const val androidxTestRules = "1.5.0"
}

object Dependencies {
    // Common
    val navigationFragment by lazy { "androidx.navigation:navigation-fragment-ktx:${Versions.navArgs}" }
    val navigationUi by lazy { "androidx.navigation:navigation-ui-ktx:${Versions.navArgs}" }
    val coreKtx by lazy { "androidx.core:core-ktx:${Versions.coreKtx}" }
    val androidMaterial by lazy { "com.google.android.material:material:${Versions.material}" }
    val appCompat by lazy { "androidx.appcompat:appcompat:${Versions.appCompat}" }
    val constraintLayout by lazy { "androidx.constraintlayout:constraintlayout:${Versions.constraintLayout}" }
    val cardView by lazy { "androidx.cardview:cardview:${Versions.cardView}" }
    val activityKtx by lazy { "androidx.activity:activity-ktx:${Versions.activityKtx}" }

    // Lifecycle
    val lifecycleExtension by lazy { "android.arch.lifecycle:extensions:${Versions.lifecycleArch}" }
    val lifecycleViewModelKtx by lazy { "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.lifecycle}" }
    val lifecycleViewModelSavedState by lazy { "androidx.lifecycle:lifecycle-viewmodel-savedstate:${Versions.lifecycle}" }
    val lifecycleReactiveStreamsKtx by lazy { "androidx.lifecycle:lifecycle-reactivestreams-ktx:${Versions.lifecycle}" }
    val lifecycleLiveDataKtx by lazy { "androidx.lifecycle:lifecycle-livedata-ktx:${Versions.lifecycle}" }
    val lifecycleProcess by lazy { "androidx.lifecycle:lifecycle-process:${Versions.lifecycle}" }
    val lifecycleRuntimeKtx by lazy { "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.lifecycle}" }
    val lifecycleCommonJava8 by lazy { "androidx.lifecycle:lifecycle-common-java8:${Versions.lifecycle}" }

    // Kotlin - Coroutines
    val kotlinStdLib by lazy { "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${Versions.kotlin}" }
    val kotlinExtensionsRuntime by lazy { "org.jetbrains.kotlin:kotlin-android-extensions-runtime:${Versions.kotlin}" }
    val coroutines by lazy { "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutines}" }
    val coroutinesCore by lazy { "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutines}" }

    // Hilt
    val hilt by lazy { "com.google.dagger:hilt-android:${Versions.hilt}" }
    val hiltCompiler by lazy { "com.google.dagger:hilt-android-compiler:${Versions.hilt}" }

    // Glide
    val glide by lazy { "com.github.bumptech.glide:glide:${Versions.glide}" }
    val glideCompiler by lazy { "com.github.bumptech.glide:compiler:${Versions.glide}" }

    // Google (Firebase, ML-KIT, GSON)
    val firebaseBom by lazy { "com.google.firebase:firebase-bom:${Versions.firebaseBom}" }
    val mlKitTextRecognition by lazy { "com.google.mlkit:text-recognition:${Versions.mlKitTextRecognition}" }
    val gson by lazy { "com.google.code.gson:gson:${Versions.gson}" }

    // CameraX
    val camera2 by lazy { "androidx.camera:camera-camera2:${Versions.cameraX}" }
    val cameraLifecycle by lazy { "androidx.camera:camera-lifecycle:${Versions.cameraX}" }
    val cameraView by lazy { "androidx.camera:camera-view:${Versions.cameraX}" }

    // CameraX
    val jUnit by lazy { "junit:junit:${Versions.jUnit}" }
    val jUnitExt by lazy { "androidx.test.ext:junit:${Versions.jUnitExt}" }
    val espressoCore by lazy { "androidx.test.espresso:espresso-core:${Versions.espresso}" }
    val ioMockk by lazy { "io.mockk:mockk:${Versions.mockk}" }
    val ioMockkAndroid by lazy { "io.mockk:mockk-android:${Versions.mockk}" }
    val hiltTest by lazy { "com.google.dagger:hilt-android-testing:${Versions.hiltTest}" }
    val androidxTestRules by lazy { "androidx.test:rules:${Versions.androidxTestRules}" }
}