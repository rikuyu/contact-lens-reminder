buildscript {
    dependencies {
        classpath("com.android.tools.build:gradle:7.1.0")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.6.10")
        classpath("com.google.dagger:hilt-android-gradle-plugin:2.39.1")
        classpath("com.google.gms:google-services:4.3.13")
        classpath("com.google.firebase:firebase-crashlytics-gradle:2.9.1")
    }
}

plugins {
    id("com.android.application") version "7.1.0" apply false
    id("com.android.library") version "7.2.2" apply false
    id("org.jetbrains.kotlin.android") version "1.5.21" apply false
}

tasks.register("clean",Delete::class){
    delete(rootProject.buildDir)
}