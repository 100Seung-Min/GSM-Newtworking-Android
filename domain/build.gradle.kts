plugins {
    id("gsm-networking-core")
}

android {
    namespace = "com.gsm.networking.domain"
}

dependencies {
    implementation(libs.coroutines.core)
}