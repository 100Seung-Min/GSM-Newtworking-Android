import com.android.build.gradle.internal.cxx.configure.gradleLocalProperties

plugins {
    id("gsm-networking-core")
}

android {
    namespace = "com.gsm.networking.remote"
    defaultConfig {
        buildConfigField(
            "String",
            "BASE_URL",
            gradleLocalProperties(rootDir).getProperty("BASE_URL")
        )
    }
}

dependencies {
    implementation(project(":data"))
    implementation(libs.bundles.retrofit)
    implementation(libs.bundles.okhttp)
}