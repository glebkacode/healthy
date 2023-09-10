@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    id("android-library-convention")
    alias(libs.plugins.kotlinSerialization)
    alias(libs.plugins.detekt)
}

android {
    namespace = "com.i.navigation"
}

dependencies {
    implementation(platform(libs.compose.bom))
    implementation(libs.ui)
    implementation(libs.ui.graphics)
    implementation(libs.ui.tooling.preview)
    implementation(libs.material3)
    implementation(libs.coroutine)
    implementation(libs.koinCore)
    implementation(libs.koinCompose)
    detektPlugins(libs.detektFormatting)
}