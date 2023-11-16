plugins {
    id("gsm-networking-core")
}

android {
    namespace = "com.gsm.networking.data"
}

dependencies {
    implementation(project(":domain"))
    implementation(libs.bundles.retrofit)
    implementation(libs.bundles.okhttp)
}