// Dependency version management
val coreKtxVersion = "1.12.0"
val lifecycleRuntimeKtxVersion = "2.6.2"
val activityComposeVersion = "1.8.2"
val constraintLayoutVersion = "2.1.4"
val constraintLayoutComposeVersion = "1.0.1"
val appCompatVersion = "1.6.1"
val lottieVersion = "6.1.0"
val materialVersion = "1.11.0"
val firebaseDatabaseKtxVersion = "20.3.0"
val firebaseAuthKtxVersion = "22.3.0"
val jUnitVersion = "4.13.2"
val plantUmlVersion = "8059"

plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("com.google.gms.google-services")
}

android {
    namespace = "com.example.testeppie"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.testeppie"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
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
        kotlinCompilerExtensionVersion = "1.4.3"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    // BASE
    implementation("androidx.core:core-ktx:$coreKtxVersion")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:$lifecycleRuntimeKtxVersion")
    implementation("androidx.activity:activity-compose:$activityComposeVersion")
    implementation(platform("androidx.compose:compose-bom:2023.03.00"))
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.compose.material3:material3")

    // CUSTOM
    implementation("androidx.constraintlayout:constraintlayout:$constraintLayoutVersion")
    // To use constraintlayout in compose
    implementation("androidx.constraintlayout:constraintlayout-compose:$constraintLayoutComposeVersion")
    implementation("androidx.appcompat:appcompat:$appCompatVersion")
    // Animations
    implementation("com.airbnb.android:lottie:$lottieVersion")
    implementation("com.google.android.material:material:$materialVersion")
    implementation("com.google.firebase:firebase-database-ktx:$firebaseDatabaseKtxVersion")
    implementation("com.google.firebase:firebase-auth-ktx:$firebaseAuthKtxVersion")
    // Documentation
    implementation("net.sourceforge.plantuml:plantuml:$plantUmlVersion")

    // TEST
    testImplementation("junit:junit:$jUnitVersion")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    androidTestImplementation(platform("androidx.compose:compose-bom:2023.03.00"))
    androidTestImplementation("androidx.compose.ui:ui-test-junit4")

    // DEBUG
    debugImplementation("androidx.compose.ui:ui-tooling")
    debugImplementation("androidx.compose.ui:ui-test-manifest")
}
