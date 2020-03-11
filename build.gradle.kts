
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
        maven {
            url = uri("https://maven.google.com")
        }
    }
}


val clean by tasks.creating(Delete::class) {
    delete = setOf(rootProject.buildDir)
}