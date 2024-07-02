plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    id("com.google.gms.google-services") version "4.4.1" apply true
}

android {
    namespace = "com.wposs.mvpapiartistfirebase"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.wposs.mvpapiartistfirebase"
        minSdk = 25
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildFeatures {
        buildConfig = true
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            buildConfigField("String", "Base_Url", "\"http://ws.audioscrobbler.com/2.0/\"")
            buildConfigField("String", "Api_Key", "\"&country=spain&api_key=829751643419a7128b7ada50de590067&format=json\"")
        }
        getByName("debug") {
            isDebuggable = true
            buildConfigField("String", "Base_Url", "\"http://ws.audioscrobbler.com/2.0/\"")
            buildConfigField("String", "Api_Key", "\"&country=spain&api_key=829751643419a7128b7ada50de590067&format=json\"")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.androidx.navigation.fragment.ktx)
    implementation(libs.androidx.navigation.ui.ktx)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    //Gson
    implementation(libs.gson)
    //imagenes ciculares
    implementation(libs.circleimageview)
    //firebase
    implementation(libs.firebase.analytics)
    implementation(libs.firebase.core)
    implementation(libs.firebase.auth)
    //retrofit
    implementation(libs.retrofit)
    implementation(libs.converter.gson)
    //libreria picasso
    implementation(libs.picasso)
}