import java.io.FileInputStream
import java.util.*

plugins {
    id("com.android.application")
    id("com.google.gms.google-services")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
    id("dagger.hilt.android.plugin")
    id("com.google.firebase.crashlytics")
}

fun getGoogleFormId(countryCode: String): String {
    val fis = FileInputStream(project.rootProject.file("local.properties"))
    val prop = Properties()
    prop.load(fis)
    return prop.getProperty("GOOGLE_FORM_ID_$countryCode")
}

android {
    compileSdk = 32

    defaultConfig {
        applicationId = "io.github.rikuyu.contactlensreminder"
        minSdk = 26
        targetSdk = 32
        versionCode = 11
        versionName = "2.3.1"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        vectorDrawables {
            useSupportLibrary = true
        }

        buildConfigField("String", "GOOGLE_FORM_ID_JA", getGoogleFormId("JA"))
        buildConfigField("String", "GOOGLE_FORM_ID_EN", getGoogleFormId("EN"))
        buildConfigField("String", "GOOGLE_FORM_ID_KO", getGoogleFormId("KO"))
        buildConfigField("String", "GOOGLE_FORM_ID_ZH", getGoogleFormId("ZH"))
    }

    buildTypes {
        release {
            isMinifyEnabled = true
            isShrinkResources = true
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.3.0"
    }
    packagingOptions {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    lint {
        xmlReport = true
        baseline = file("lint-baseline.xml")
    }
    testOptions.unitTests.isIncludeAndroidResources = true
}

val ktlint by configurations.creating

dependencies {

    implementation("androidx.core:core-ktx:1.8.0")
    implementation("androidx.compose.ui:ui:1.2.1")
    implementation("androidx.appcompat:appcompat:1.5.0")
    implementation("androidx.compose.material:material:1.2.1")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.5.1")
    implementation("androidx.activity:activity-compose:1.5.1")

    testImplementation("androidx.test.ext:junit-ktx:1.1.3")
    testImplementation("androidx.test:core-ktx:1.4.0")
    testImplementation("androidx.arch.core:core-testing:2.1.0")
    testImplementation("com.google.truth:truth:1.1.3")
    testImplementation("org.robolectric:robolectric:4.8.2")
    testImplementation("io.mockk:mockk:1.12.7")
    testImplementation("io.mockk:mockk-agent-jvm:1.12.7")

    implementation("androidx.navigation:navigation-compose:2.5.1")

    implementation("io.github.vanpra.compose-material-dialogs:datetime:0.7.2")
    implementation("com.chargemap.compose:numberpicker:1.0.3")
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.5.1")
    implementation("com.google.accompanist:accompanist-pager:0.25.1")
    implementation("com.google.accompanist:accompanist-pager-indicators:0.25.1")
    implementation("com.google.accompanist:accompanist-systemuicontroller:0.25.1")

    implementation("com.google.dagger:hilt-android:2.43.2")
    kapt("com.google.dagger:hilt-android-compiler:2.43.2")
    implementation("androidx.hilt:hilt-navigation-compose:1.0.0")

    implementation(platform("com.google.firebase:firebase-bom:30.3.2"))
    implementation("com.google.firebase:firebase-analytics-ktx")
    implementation("com.google.firebase:firebase-crashlytics-ktx")

    implementation("com.google.android.play:core:1.10.3")
    implementation("com.google.android.play:core-ktx:1.8.1")

    ktlint("com.pinterest:ktlint:0.47.0") {
        attributes {
            attribute(Bundling.BUNDLING_ATTRIBUTE, objects.named(Bundling.EXTERNAL))
        }
    }
}

val outputDir = "${project.buildDir}/reports/"
val inputFiles = project.fileTree(mapOf("dir" to "src", "include" to "**/*.kt"))

val ktlintCheck by tasks.creating(JavaExec::class) {
    inputs.files(inputFiles)
    outputs.dir(outputDir)

    description = "Check Kotlin code style."
    classpath = ktlint
    mainClass.set("com.pinterest.ktlint.Main")
    args = listOf("--android",
        "--color",
        "--reporter=plain",
        "--reporter=checkstyle,output=${buildDir}/reports/ktlint-result.xml",
        "src/**/*.kt")
    isIgnoreExitValue = true
}

val ktlintFormat by tasks.creating(JavaExec::class) {
    inputs.files(inputFiles)
    outputs.dir(outputDir)

    description = "Fix Kotlin code style deviations."
    classpath = ktlint
    mainClass.set("com.pinterest.ktlint.Main")
    args = listOf("-F", "src/**/*.kt")
    isIgnoreExitValue = true
}