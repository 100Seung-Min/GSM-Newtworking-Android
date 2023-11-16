plugins {
    id("gsm-networking-core")
}

android {
    namespace = "com.gsm.networking.local"
}

dependencies {
    implementation(project(":data"))
    implementation(libs.androidx.preference)
}