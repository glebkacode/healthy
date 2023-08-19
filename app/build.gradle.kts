@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    id("android-application-convention")
    alias(libs.plugins.detekt)
}

android {
    namespace = "com.i.healthy"
    defaultConfig {
        applicationId = "com.i.healthy"
    }
}

dependencies {
    implementation(project(":feature:auth:auth-impl"))
    implementation(project(":feature:records:records-impl"))
    implementation(libs.core.ktx)
    implementation(libs.lifecycle.runtime.ktx)
    implementation(libs.activity.compose)
    implementation(platform(libs.compose.bom))
    implementation(libs.ui)
    implementation(libs.ui.graphics)
    implementation(libs.ui.tooling.preview)
    implementation(libs.material3)
    implementation(libs.navigation)
    implementation(libs.koinCore)
    implementation(libs.koinCompose)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.test.ext.junit)
    androidTestImplementation(libs.espresso.core)
    androidTestImplementation(platform(libs.compose.bom))
    androidTestImplementation(libs.ui.test.junit4)
    debugImplementation(libs.ui.tooling)
    debugImplementation(libs.ui.test.manifest)
    detektPlugins(libs.detektFormatting)
}
