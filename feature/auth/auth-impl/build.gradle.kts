@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    id("android-library-convention")
    alias(libs.plugins.detekt)
}

android {
    namespace = "com.i.auth_impl"
}

dependencies {
    implementation(project(":core:core-architecture"))
    implementation(project(":core:navigation"))
    implementation(platform(libs.compose.bom))
    implementation(libs.ui)
    implementation(libs.ui.graphics)
    implementation(libs.ui.tooling.preview)
    implementation(libs.material3)
    implementation(libs.navigation)
    implementation(libs.koinCore)
    implementation(libs.koinCompose)
    implementation(libs.retrofit)
    implementation(libs.okhttp)
    implementation(libs.okhttpLogging)
    implementation(libs.kotlinSerialization)
    implementation(libs.kotlinSerializationConverter)

    implementation(libs.mvikotlin.core)
    implementation(libs.mvikotlin.main)
    implementation(libs.mvikotlin.timetravel)
    implementation(libs.mvikotlin.coroutine)
    implementation(libs.mvikotlin.logging)

    testImplementation(libs.testing.unit.junit.jupiter.api)
    testRuntimeOnly(libs.testing.unit.junit.jupiter.engine)
    testImplementation(libs.testing.unit.assertk)
    testImplementation(libs.testing.unit.assertk.coroutine)
    testImplementation(libs.testing.unit.turbine)
    testImplementation(libs.testing.unit.mockk)
    testImplementation(libs.testing.unit.coroutine)
    testImplementation(libs.testing.unit.kotest.junit5runner)
    testImplementation(libs.testing.unit.kotest.assertions)
    testImplementation(libs.testing.unit.kotest.property)
    androidTestImplementation(libs.androidx.test.ext.junit)
    androidTestImplementation(libs.espresso.core)
    androidTestImplementation(platform(libs.compose.bom))
    androidTestImplementation(libs.ui.test.junit4)
    debugImplementation(libs.ui.tooling)
    debugImplementation(libs.ui.test.manifest)
    detektPlugins(libs.detektFormatting)
}
