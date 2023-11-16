plugins {
    id("gsm-networking-core")
}

android {
    namespace = "com.gsm.networking.di"
    defaultConfig {
        buildConfigField(
            "String",
            "BASE_URL",
            com.android.build.gradle.internal.cxx.configure.gradleLocalProperties(rootDir)
                .getProperty("BASE_URL")
        )
    }
}

dependencies {
    implementation(project(":data"))
    implementation(project(":domain"))
    implementation(libs.androidx.preference)
    implementation(libs.bundles.retrofit)
}