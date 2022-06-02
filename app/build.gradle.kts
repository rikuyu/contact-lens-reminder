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

//def getGoogleFormIdJa() {
//    def propFile = project.rootProject.file("local.properties")
//    def properties = new Properties()
//    properties.load(propFile.newDataInputStream())
//    return properties["GOOGLE_FORM_ID_JA"]
//}
//
//def getGoogleFormIdEn() {
//    def propFile = project.rootProject.file("local.properties")
//    def properties = new Properties()
//    properties.load(propFile.newDataInputStream())
//    return properties["GOOGLE_FORM_ID_EN"]
//}
//
//def getGoogleFormIdKo() {
//    def propFile = project.rootProject.file("local.properties")
//    def properties = new Properties()
//    properties.load(propFile.newDataInputStream())
//    return properties["GOOGLE_FORM_ID_KO"]
//}
//
//def getGoogleFormIdZh() {
//    def propFile = project.rootProject.file("local.properties")
//    def properties = new Properties()
//    properties.load(propFile.newDataInputStream())
//    return properties["GOOGLE_FORM_ID_ZH"]
//}

//def versionPropertiesFile = file("${project.rootDir.absolutePath}/ci_config/version.properties")
//def versionProperties = new Properties()
//if (versionPropertiesFile.exists()) {
//    versionProperties.load(versionPropertiesFile.newReader())
//}
//
//final
//def generatedVersionCode = versionProperties.getProperty("versionCode", project.defaultVersionCode).toInteger()
//final
//def generatedVersionName = versionProperties.getProperty("versionName", project.defaultVersionName)
//
//val fis = FileInputStream("YOUR_PROPERTIES_FILE_PATH")
//val prop = Properties()
//prop.load(fis)
//println("Value is =" + prop.getProperty("propertyName"))

fun getGoogleFormId(countryCode: String): String {
    val fis = FileInputStream(project.rootProject.file("local.properties"))
    val prop = Properties()
    prop.load(fis)
    return prop.getProperty("GOOGLE_FORM_ID_$countryCode")
}

android {
    compileSdk = 31

    defaultConfig {
        applicationId = "io.github.rikuyu.contactlensreminder"
        minSdk = 26
        targetSdk = 31
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
        kotlinCompilerExtensionVersion = "1.1.1"
    }
    packagingOptions {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    lintOptions {
        xmlReport = true
    }
    testOptions.unitTests.isIncludeAndroidResources = true
}

//configurations {
//    ktlint
//}

dependencies {

    implementation("androidx.core:core-ktx:1.7.0")
    implementation("androidx.compose.ui:ui:1.1.1")
    implementation("androidx.appcompat:appcompat:1.4.1")
    implementation("com.google.android.material:material:1.5.0")
    implementation("androidx.compose.material:material:1.1.1")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.4.1")
    implementation("androidx.activity:activity-compose:1.4.0")

    testImplementation("androidx.test.ext:junit-ktx:1.1.3")
    testImplementation("androidx.test:core-ktx:1.4.0")
    testImplementation("androidx.arch.core:core-testing:2.1.0")
    testImplementation("com.google.truth:truth:1.1.3")
    testImplementation("org.robolectric:robolectric:4.7.3")
    testImplementation("io.mockk:mockk:1.12.2")
    testImplementation("io.mockk:mockk-agent-jvm:1.12.2")

    implementation("androidx.navigation:navigation-compose:2.4.2")

    implementation("io.github.vanpra.compose-material-dialogs:datetime:0.7.0")
    implementation("com.chargemap.compose:numberpicker:0.0.11")
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.4.1")
    implementation("com.google.accompanist:accompanist-pager:0.23.1")
    implementation("com.google.accompanist:accompanist-pager-indicators:0.23.1")
    implementation("com.google.accompanist:accompanist-systemuicontroller:0.23.1")

    implementation("com.google.dagger:hilt-android:2.40")
    kapt("com.google.dagger:hilt-android-compiler:2.40")
    implementation("androidx.hilt:hilt-lifecycle-viewmodel:1.0.0-alpha01")
    kapt("androidx.hilt:hilt-compiler:1.0.0")
    implementation("androidx.hilt:hilt-navigation-compose:1.0.0")

    implementation(platform("com.google.firebase:firebase-bom:29.0.3"))
    implementation("com.google.firebase:firebase-analytics-ktx")
    implementation("com.google.firebase:firebase-crashlytics-ktx")

    implementation("com.google.android.play:core:1.10.3")
    implementation("com.google.android.play:core-ktx:1.8.1")

//    ktlint("com.pinterest:ktlint:0.42.1")
}

//task ktlint(type: JavaExec, group: "verification") {
//    description = "Check Kotlin code style."
//    classpath = configurations.ktlint
//    main = "com.pinterest.ktlint.Main"
//    args "--android", "--color", "--reporter=plain", "--reporter=checkstyle,output=${buildDir}/reports/ktlint-result.xml", "src/**/*.kt"
//    ignoreExitValue true
//}
//check.dependsOn ktlint
//
//task ktlintFormat(type: JavaExec, group: "formatting") {
//    description = "Fix Kotlin code style deviations."
//    classpath = configurations.ktlint
//    main = "com.pinterest.ktlint.Main"
//    args "-F", "src/**/*.kt"
//    ignoreExitValue true
//}
