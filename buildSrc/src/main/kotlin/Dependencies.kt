object BuildModules {
    const val coreModule = ":core"
}

object BuildPlugins {
    //All the build plugins are added here
    const val androidApplication = "com.android.application"
    const val androidLibrary = "com.android.library"
    const val kapt = "kotlin-kapt"
    const val ktlintPlugin = "org.jlleitschuh.gradle.ktlint"
    const val kotlinAndroid = "org.jetbrains.kotlin.android"
    const val kotlinAndroidExtensions = "org.jetbrains.kotlin.android.extensions"
}

object AndroidSdk {
    const val minSdkVersion = 21
    const val compileSdkVersion = 33
    const val targetSdkVersion = compileSdkVersion
    const val versionCode = 1
    const val versionName = "1.0.0"
}

object Versions {
    // Material and androidX
    const val material = "1.7.0"
    const val appCompat = "1.5.1"
    const val constraintLayout = "2.1.4"
    const val swiperefreshlayout = "1.2.0-alpha01"


    //Networking
    const val retrofit = "2.9.0"
    const val okhttp = "4.8.1"
    const val loggingInterceptor = "4.8.1"
    const val kotlinSerializationJson = "1.3.2"
    const val retrofitKotlinSerialization = "0.8.0"

    //DI - Hilt
    const val hilt = "2.44"
    const val hiltViewModel = "1.0.0-alpha03"

    // Glide
    const val glideVersion = "4.11.0"

    // Room db
    const val room = "2.3.0-alpha02"
    const val store = "4.0.0"

    // Lifecycle
    const val lifecycle = "2.5.1"

    // Coroutines
    const val coroutines = "1.3.9"

    // Logging - debug builds
    const val timber = "4.7.1"

    // Kotlin
    const val kotlinVersion = "1.4.0"
    const val coreKtx = "1.9.0"

    //store
    const val dataStore = "1.0.0"

    // tests
    const val junit = "4.13.2"
    const val junitTest = "1.1.4"
    const val espresso = "3.5.0"
    const val roboelectric = "4.4-beta-1"
    const val androidXJUnit = "1.1.4"
    const val truth = "1.0.1"
    const val mockWebServer = "4.8.1"
    const val androidXTestCore = "1.3.0"
    const val runner = "1.3.0"
    const val rules = "1.3.0"
    const val archComponentTest = "2.1.0"
    const val mockK = "1.10.0"
    const val liveDataTesting = "1.1.2"

}

object Libraries {
    // androidX and Material
    const val material = "com.google.android.material:material:${Versions.material}"
    const val appCompat = "androidx.appcompat:appcompat:${Versions.appCompat}"
    const val swiperefreshlayout =
        "androidx.swiperefreshlayout:swiperefreshlayout:${Versions.swiperefreshlayout}"
    const val constraintLayout =
        "androidx.constraintlayout:constraintlayout:${Versions.constraintLayout}"


    // Networking -  OKHTTP and loggingInterceptor
    const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
    const val gson = "com.squareup.retrofit2:converter-gson:${Versions.retrofit}"
    const val ohttp = "com.squareup.okhttp3:okhttp:${Versions.okhttp}"
    const val loggingInterceptor =
        "com.squareup.okhttp3:logging-interceptor:${Versions.loggingInterceptor}"
    const val retrofitCoroutineAdapter =
        "com.jakewharton.retrofit:retrofit2-kotlin-coroutines-adapter:0.9.2"
    const val mockWebserver = "com.squareup.okhttp3:mockwebserver:4.9.3"
    const val retrofitKotlinSerialization =
        "com.jakewharton.retrofit:retrofit2-kotlinx-serialization-converter:${Versions.retrofitKotlinSerialization}"
    const val kotlinSerializationJson =
        "org.jetbrains.kotlinx:kotlinx-serialization-json:${Versions.kotlinSerializationJson}"

    // DI - dagger hilt
    const val hilt = "com.google.dagger:hilt-android:${Versions.hilt}"
    const val hiltLifecycleViewmodel = "androidx.hilt:hilt-lifecycle-viewmodel:${Versions.hiltViewModel}"
    const val hiltCompiler = "com.google.dagger:hilt-compiler:${Versions.hilt}"

    //store
    const val dataStore = "androidx.datastore:datastore-preferences:${Versions.dataStore}"

    // Glide
    const val glide = "com.github.bumptech.glide:glide:${Versions.glideVersion}"
    const val glideCompiler = "com.github.bumptech.glide:compiler:${Versions.glideVersion}"

    // Room db
    const val roomRuntime = "androidx.room:room-runtime:${Versions.room}"
    const val room = "androidx.room:room-ktx:${Versions.room}"
    const val roomCompiler = "androidx.room:room-compiler:${Versions.room}"
    const val store = "com.dropbox.mobile.store:store4:${Versions.store}"

    // Lifecycle
    const val viewModel = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.lifecycle}"
    const val livedata = "androidx.lifecycle:lifecycle-livedata-ktx:${Versions.lifecycle}"
    const val lifecycle = "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.lifecycle}"
    const val viewModelSavedState =
        "androidx.lifecycle:lifecycle-viewmodel-savedstate:${Versions.lifecycle}"

    // Coroutines
    const val coroutines = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutines}"
    const val coroutinesAndroid =
        "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutines}"

    // Logging - debug builds
    const val timber = "com.jakewharton.timber:timber:${Versions.timber}"

    // Kotlin
    const val kotlinStdLib = "org.jetbrains.kotlin:kotlin-stdlib:${Versions.kotlinVersion}"
    const val coreKtx = "androidx.core:core-ktx:${Versions.coreKtx}"


}

object TestLibraries {
    const val espresso = "androidx.test.espresso:espresso-core:${Versions.espresso}"
    const val jUnit = "junit:junit:${Versions.junit}"
    const val androidXJUnit = "androidx.test.ext:junit:${Versions.androidXJUnit}"

    const val roboelectric = "org.robolectric:robolectric:${Versions.roboelectric}"
    const val truth = "com.google.truth:truth:${Versions.truth}"
    const val mockWebServer = "com.squareup.okhttp3:mockwebserver:${Versions.mockWebServer}"
    const val androidXTestCore = "androidx.test:core:${Versions.androidXTestCore}"
    const val runner = "androidx.test:runner:${Versions.runner}"
    const val rules = "androidx.test:rules:${Versions.rules}"
    const val roomTest = "androidx.room:room-testing:${Versions.room}"
    const val coroutinesTest =
        "org.jetbrains.kotlinx:kotlinx-coroutines-test:${Versions.coroutines}"
    const val archComponentTest =
        "androidx.arch.core:core-testing:${Versions.archComponentTest}"
    const val mockK = "io.mockk:mockk:${Versions.mockK}"
    const val androidMockK = "io.mockk:mockk-android:${Versions.mockK}"
    const val liveDataTesting = "com.jraska.livedata:testing-ktx:${Versions.liveDataTesting}"
}