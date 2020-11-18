plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("android.extensions")
    id("kotlin-android")
}

android {
    compileSdkVersion(29)
    buildToolsVersion("29.0.3")

    defaultConfig {
        applicationId = "com.enigmacamp.goldmarket"
        minSdkVersion(23)
        targetSdkVersion(29)
        versionCode(1)
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
}
val kotlin_version = "1.4.10"
val material_version = "1.2.1"
val kotlin_coroutine_version = "1.4.1"

dependencies {
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:$kotlin_coroutine_version")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:$kotlin_coroutine_version")
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))
    implementation("org.jetbrains.kotlin:kotlin-stdlib:$kotlin_version")
    implementation("androidx.core:core-ktx:1.3.2")
    implementation("androidx.appcompat:appcompat:1.2.0")
    implementation("androidx.constraintlayout:constraintlayout:2.0.3")
    implementation("com.google.android.material:material:$material_version")
    implementation("com.android.support:design:29.0.0")
    implementation("androidx.legacy:legacy-support-v4:1.0.0")

    testImplementation("junit:junit:4.12")
    androidTestImplementation("androidx.test.ext:junit:1.1.2")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.3.0")
}