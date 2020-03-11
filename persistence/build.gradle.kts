import com.koidev.stackoverflowstars.Build
import com.koidev.stackoverflowstars.Libs

plugins {
    id("com.android.library")
    kotlin("android")
    kotlin("android.extensions")
    kotlin("kapt")
}

android {
    compileSdkVersion(Build.compileSdk)
}

dependencies {

    implementation(project(":data"))

    implementation(Libs.Kotlin.stdlib)

    implementation(Libs.RxJava.rxKotlin)
    implementation(Libs.RxJava.rxRelay)
    api(Libs.AndroidX.Room.runtime)
    implementation(Libs.AndroidX.Room.rxjava2)
    implementation(Libs.AndroidX.Room.gson)
    kapt(Libs.AndroidX.Room.compiler)

    implementation(Libs.timber)

}