import org.gradle.api.JavaVersion

object Version {
    const val COMPILE_SDK_VERSION = 33
    const val MIN_SDK_VERSION = 28
    const val TARGET_SDK_VERSION = 33
    const val VERSION_CODE = 1
    const val VERSION_NAME = "1.0.0"

    val JAVA_VERSION = JavaVersion.VERSION_1_8

    const val GRADLE_ANDROID = "7.4.2"
    const val GRADLE_KOTLIN = "1.7.20"
    const val HILT = "2.44"

    const val KOTLINX_COROUTINES = "1.6.4"

    const val CORE_KTX = "1.9.0"
    const val LIFECYCLE_KTX = "2.5.1"
    const val APP_COMPAT = "1.6.0"
    const val PREFERENCE_KTX = "1.2.0"

    const val COMPOSE_ACTIVITY = "1.6.1"
    const val COMPOSE = "1.4.3"
    const val COMPOSE_MATERIAL3 = "1.0.1"
    const val HILT_NAV = "1.0.0"

    const val WEB_VIEW = "0.24.13-rc"
    const val OAUTH = "20.1.0"

    const val RETROFIT = "2.9.0"
    const val OKHTTP = "4.10.0"

    const val JUNIT = "4.13.2"
    const val MOCKITO_KOTLIN = "4.1.0"
    const val ANDROID_JUNIT = "1.1.2"
    const val ESPRESSO_CORE = "3.3.0"
}