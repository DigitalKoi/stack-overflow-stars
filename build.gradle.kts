
buildscript {

    repositories {
        google()
        jcenter()

    }
    dependencies {
        classpath(com.koidev.stack_overflow_stars.Libs.androidGradlePlugin)
        classpath(com.koidev.stack_overflow_stars.Libs.Kotlin.gradlePlugin)
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