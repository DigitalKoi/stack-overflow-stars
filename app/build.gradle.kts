import com.koidev.stackoverflowstars.Build
import com.koidev.stackoverflowstars.Libs

plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("android.extensions")
    kotlin("kapt")
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

    flavorDimensions("environment")

    productFlavors {
        create("dev") {
            buildConfigField(
                "String",
                "DIRECTIONS_URL",
                "\"http://api.stackexchange.com/2.2/\""
            )
        }
    }

    sourceSets {
        getByName("main").java.srcDirs("src/main/java")
        getByName("main").res.srcDirs(
            "src/main/res/layout/activity",
            "src/main/res/layout/fragment",
            "src/main/res/layout/item",
            "src/main/res/layout/widget",
            "src/main/res/layout",
            "src/main/res"
        )
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
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
    implementation(Libs.AndroidX.cardview)
    implementation(Libs.AndroidX.swiperefresh)

    implementation(Libs.RxJava.rxKotlin)
    implementation(Libs.RxJava.rxAndroid)
    implementation(Libs.RxJava.rxRelay)

    implementation(Libs.Dagger.androidSupport)
    kapt(Libs.Dagger.compiler)
    kapt(Libs.Dagger.androidProcessor)

    implementation(Libs.Glide.glide)
    annotationProcessor(Libs.Glide.compiler)

    implementation(Libs.adapterDelegates)

    implementation(Libs.threetenabp)

    implementation(Libs.cicerone)

    implementation(Libs.timber)
    implementation(Libs.stetho)

    debugImplementation(Libs.leakCanary)

    testImplementation(Libs.junit)
    testImplementation(Libs.AndroidX.Test.espressoCore)

    implementation(project(":domain"))
    implementation(project(":data"))
    implementation(project(":remote"))
    implementation(project(":persistence"))
}
