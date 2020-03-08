
buildscript {

    repositories {
        google()
        jcenter()

    }
    dependencies {
        classpath(com.koidev.stackoverflowstars.Libs.androidGradlePlugin)
        classpath(com.koidev.stackoverflowstars.Libs.Kotlin.gradlePlugin)
    }
}

allprojects {
    repositories {
        google()
        jcenter()

    }
}


val clean by tasks.creating(Delete::class) {
    delete = setOf(rootProject.buildDir)
}