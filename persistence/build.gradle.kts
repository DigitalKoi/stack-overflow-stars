import com.koidev.stackoverflowstars.Build
import com.koidev.stackoverflowstars.Libs

plugins {
    id("com.android.library")
    kotlin("android")
    kotlin("android.extensions")
}

android {
    compileSdkVersion(Build.compileSdk)
}

dependencies {
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))

    implementation(project(":data"))

    implementation(Libs.Kotlin.stdlib)

    implementation(Libs.RxJava.rxKotlin)
    implementation(Libs.RxJava.rxRelay)
    implementation(Libs.AndroidX.Room.runtime)
    implementation(Libs.AndroidX.Room.rxjava2)
    implementation(Libs.AndroidX.Room.gson)
    annotationProcessor(Libs.AndroidX.Room.compiler)

    implementation(Libs.timber)

}
