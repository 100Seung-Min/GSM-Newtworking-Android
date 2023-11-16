import com.android.build.gradle.internal.cxx.configure.gradleLocalProperties

plugins {
    id("gsm-networking-app")
}

android {
    namespace = "com.gsm.networking"
    defaultConfig {
        applicationId = "com.gsm.networking"
        buildConfigField(
            "String",
            "CLIENT_ID",
            gradleLocalProperties(rootDir).getProperty("CLIENT_ID")
        )
        buildConfigField(
            "String",
            "WEBVIEW_URL",
            gradleLocalProperties(rootDir).getProperty("WEBVIEW_URL")
        )
    }
}

dependencies {
    implementation(project(":domain"))
    implementation(project(":di"))
    implementation(libs.webview)
    implementation(libs.oauth)
    implementation(libs.bundles.retrofit)
}