import com.android.build.gradle.internal.cxx.configure.gradleLocalProperties

plugins {
    id("com.android.application")
    id("kotlin-android")
    id("kotlin-kapt")
    id("dagger.hilt.android.plugin")
}

android {
    namespace = "com.gsm.networking"
    compileSdk = Version.COMPILE_SDK_VERSION

    defaultConfig {
        applicationId = "com.gsm.networking"
        minSdk = Version.MIN_SDK_VERSION
        targetSdk = Version.TARGET_SDK_VERSION
        versionCode = Version.VERSION_CODE
        versionName = Version.VERSION_NAME
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        buildConfigField(
            "String",
            "CLIENT_ID",
            gradleLocalProperties(rootDir).getProperty("CLIENT_ID")
        )
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = true
            isShrinkResources = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = Version.JAVA_VERSION
        targetCompatibility = Version.JAVA_VERSION
    }
    kotlinOptions {
        jvmTarget = Version.JAVA_VERSION.toString()
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = Version.COMPOSE
    }
    packagingOptions {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    hilt {
        enableAggregatingTask = true
    }
}

dependencies {
    implementation(Dependency.Kotlin.COROUTINES_CORE)
    implementation(Dependency.Kotlin.COROUTINES_ANDROID)

    implementation(Dependency.AndroidX.CORE_KTX)
    implementation(Dependency.AndroidX.LIFECYCLE)

    implementation(Dependency.Compose.ACTIVITY)
    implementation(Dependency.Compose.UI)
    implementation(Dependency.Compose.PREVIEW)
    implementation(Dependency.Compose.COMPOSE_HILT_NAV)
    implementation(Dependency.Compose.MATERIAL3)

    implementation(Dependency.Google.WEB_VIEW)
    implementation(Dependency.Google.OAUTH)

    implementation(Dependency.Hilt.HILT_ANDROID)
    kapt(Dependency.Hilt.HILT_ANDROID_COMPILER)

    implementation(Dependency.Retrofit.RETROFIT)
    implementation(Dependency.Retrofit.RETROFIT_CONVERTER_GSON)

    debugImplementation(Dependency.AndroidTest.COMPOSE_TOOL)
    debugImplementation(Dependency.AndroidTest.COMPOSE_MANIFEST)
    androidTestImplementation(Dependency.AndroidTest.COMPOSE_TEST)
}