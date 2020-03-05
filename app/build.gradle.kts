import com.koidev.stack_overflow_stars.Build
import com.koidev.stack_overflow_stars.Libs

plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("android.extensions")
    id("kotlin-kapt")
}

android {
    compileSdkVersion(Build.compileSdk)
    defaultConfig {
        applicationId = Build.appId
        minSdkVersion(Build.minSdk)
        targetSdkVersion(Build.compileSdk)
        versionCode = Build.versionCode
        versionName = Build.versionName
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    //TODO: add build types
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile(
                    "proguard-android-optimize.txt"
                ),
                "proguard-rules.pro"
            )
        }
    }

}

dependencies {
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))
    implementation(Libs.Kotlin.stdlib)

    implementation(Libs.AndroidX.appcompat)
    implementation(Libs.AndroidX.constraintlayout)

    implementation(Libs.AndroidX.Lifecycle.extensions)
    implementation(Libs.AndroidX.Lifecycle.viewmodelKtx)
    implementation(Libs.AndroidX.Fragment.fragmentKtx)
    implementation(Libs.AndroidX.Navigation.fragment)
    implementation(Libs.AndroidX.Navigation.ui)
    implementation(Libs.Google.material)


    implementation(Libs.Dagger.androidSupport)
    kapt(Libs.Dagger.compiler)
    kapt(Libs.Dagger.androidProcessor)

    implementation(Libs.timber)

    debugImplementation(Libs.leakCanary)

    testImplementation(Libs.junit)
    testImplementation(Libs.AndroidX.Test.espressoCore)

    implementation(project(":domain"))
    implementation(project(":data"))
    implementation(project(":remote"))
    implementation(project(":persistence"))
}
