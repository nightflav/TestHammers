@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.org.jetbrains.kotlin.android)
    alias(libs.plugins.ksp)
}

android {
    namespace = "com.example.royaal.testhammers"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.royaal.testhammers"
        minSdk = 26
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
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.3"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
//Android + Compose
    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.compose.runtime)
    implementation(libs.androidx.lifecycle.runtimeCompose)
    implementation(libs.androidx.navigation.compose)
    implementation(libs.coil.kt)
    implementation(libs.androidx.compose.material3)
    implementation(libs.androidx.core.splash)
    implementation(libs.androidx.dataStore)
    implementation(libs.androidx.dataStore.core)
    implementation(libs.androidx.compose.ui.tooling.preview)
    //DI
    implementation(libs.dagger.kt)
    implementation(project(mapOf("path" to ":feature_main:api")))
    ksp(libs.dagger.compiler)
    //Serialization
    implementation(libs.retrofit.kotlin.serialization)
    //Modules
    implementation(project(":core:common"))
    implementation(project(":core:common_compose"))
    implementation(project(":core:network"))
    implementation(project(":core:database"))
    implementation(project(":feature_main:api"))
    implementation(project(":feature_main:data"))
    implementation(project(":feature_main:domain"))
    implementation(project(":feature_main:presentation"))
}