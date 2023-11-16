import com.android.build.gradle.internal.cxx.configure.gradleLocalProperties

plugins {
    id("gsm-networking-core")
}

android {
    namespace = "com.gsm.networking.data"
    defaultConfig {
        buildConfigField(
            "String",
            "BASE_URL",
            gradleLocalProperties(rootDir).getProperty("BASE_URL")
        )
    }
}

dependencies {
    implementation(project(":domain"))
    implementation(libs.bundles.retrofit)
    implementation(libs.bundles.okhttp)
}