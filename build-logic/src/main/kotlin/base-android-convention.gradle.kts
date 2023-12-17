import com.android.build.gradle.BaseExtension

configure<BaseExtension> {
    compileSdkVersion(34)

    defaultConfig {
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

    buildFeatures.compose = true
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.3"
    }

    testOptions {
        unitTests.all {
            it.useJUnitPlatform()
        }
    }

    packagingOptions {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    lintOptions {
        disable += "MutableCollectionMutableState"
    }
}
