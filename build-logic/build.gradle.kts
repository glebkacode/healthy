plugins {
    `kotlin-dsl`
}

repositories {
    mavenCentral()
    google()
}

dependencies {
    implementation(libs.androidGradle)
    implementation(libs.kotlinGradle)
}
