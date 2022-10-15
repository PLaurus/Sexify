import org.gradle.api.internal.artifacts.dependencies.DefaultExternalModuleDependency

plugins {
    kotlin("multiplatform")
    id("com.android.library")
    kotlin("kapt")
}

group = "com.lauruscorp"
version = "1.0-SNAPSHOT"

kotlin {
    jvm {
        compilations.all {
            kotlinOptions.jvmTarget = "11"
        }
        testRuns["test"].executionTask.configure {
            useJUnitPlatform()
        }
    }

    android()

    val hostOs = System.getProperty("os.name")
    val isMingwX64 = hostOs.startsWith("Windows")
    val nativeTarget = when {
        hostOs == "Mac OS X" -> macosX64("native")
        hostOs == "Linux" -> linuxX64("native")
        isMingwX64 -> mingwX64("native")
        else -> throw GradleException("Host OS is not supported in Kotlin/Native.")
    }

    listOf(
        iosX64(),
        iosArm32(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach {
        it.binaries.framework {
            baseName = "core"
        }
    }

    sourceSets {
        val commonMain by getting {
            dependencies {
                // Asynchronous programming
                // https://github.com/Kotlin/kotlinx.coroutines/releases
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.4")

                // MVI
                // https://github.com/arkivanov/MVIKotlin/releases/tag/3.0.2
                val mviKotlinVersion = "3.0.2"
                implementation("com.arkivanov.mvikotlin:mvikotlin:$mviKotlinVersion")
                implementation("com.arkivanov.mvikotlin:mvikotlin-main:$mviKotlinVersion")
                implementation("com.arkivanov.mvikotlin:mvikotlin-logging:$mviKotlinVersion")
                implementation("com.arkivanov.mvikotlin:mvikotlin-timetravel:$mviKotlinVersion")
                implementation("com.arkivanov.mvikotlin:mvikotlin-extensions-coroutines:$mviKotlinVersion")
            }
        }

        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
            }
        }

        val jvmMain by getting {
            dependencies {
                // Asynchronous programming
                // https://github.com/Kotlin/kotlinx.coroutines/releases
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core-jvm:1.6.4")

                // Apache commons
                // IO
                // https://mvnrepository.com/artifact/commons-io/commons-io
                implementation("commons-io:commons-io:2.11.0")
                // Text
                implementation("org.apache.commons:commons-text:1.9")

                // Reflection
                // https://mvnrepository.com/artifact/org.jetbrains.kotlin/kotlin-reflect
                implementation("org.jetbrains.kotlin:kotlin-reflect:1.7.10")

                // Dependency injection
                // https://github.com/google/dagger/releases
                implementation("com.google.dagger:dagger:2.44")
                configurations["kapt"].dependencies.add(
                    DefaultExternalModuleDependency(
                        "com.google.dagger",
                        "dagger-compiler",
                        "2.44"
                    )
                )
            }
        }

        val nativeMain by getting

        val androidMain by getting {
            dependsOn(jvmMain)
        }

        val iosX64Main by getting
        val iosArm32Main by getting
        val iosArm64Main by getting
        val iosSimulatorArm64Main by getting

        val iosMain by creating {
            dependsOn(nativeMain)
            iosX64Main.dependsOn(this)
            iosArm32Main.dependsOn(this)
            iosArm64Main.dependsOn(this)
            iosSimulatorArm64Main.dependsOn(this)
        }
    }
}

android {
    compileSdk = 33
    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")

    defaultConfig {
        minSdk = 21
        targetSdk = 33

        vectorDrawables.useSupportLibrary = true

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

    buildFeatures {
        viewBinding = true
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = "1.3.0"
    }

    namespace = "com.lauruscorp.core"

    dependencies {
        // Asynchronous programming
        // https://github.com/Kotlin/kotlinx.coroutines/releases
        implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.6.4")

        // ViewModel
        // https://mvnrepository.com/artifact/androidx.lifecycle/lifecycle-viewmodel-ktx
        implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.6.0-alpha02")

        // Android Platform
        // https://mvnrepository.com/artifact/androidx.core/core-ktx
        implementation("androidx.core:core-ktx:1.9.0")
        // https://mvnrepository.com/artifact/androidx.appcompat/appcompat
        implementation("androidx.appcompat:appcompat:1.5.1")
        // https://mvnrepository.com/artifact/androidx.fragment/fragment-ktx
        implementation("androidx.fragment:fragment-ktx:1.5.3")

        // UI
        val composeVersion = "1.2.1"
        // https://github.com/material-components/material-components-android/releases
        implementation("com.google.android.material:material:1.8.0-alpha01")
        // https://developer.android.com/jetpack/androidx/releases/compose
        implementation("androidx.compose.ui:ui:$composeVersion")
        implementation("androidx.compose.ui:ui-tooling-preview:$composeVersion")
        implementation("androidx.compose.foundation:foundation:$composeVersion")
        implementation("androidx.compose.material:material-icons-core:$composeVersion")
        implementation("androidx.compose.material:material-icons-extended:$composeVersion")
        debugImplementation("androidx.compose.ui:ui-tooling:$composeVersion")
        debugImplementation("androidx.compose.ui:ui-test-manifest:$composeVersion")
        androidTestImplementation("androidx.compose.ui:ui-test-junit4:$composeVersion")
        // https://developer.android.com/jetpack/androidx/releases/compose-material3
        implementation("androidx.compose.material3:material3:1.0.0-rc01")
        // https://mvnrepository.com/artifact/androidx.activity/activity-compose
        implementation("androidx.activity:activity-compose:1.6.0")
        implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.5.1")
        implementation("androidx.compose.runtime:runtime-livedata:1.2.1")

        // Testing
        testImplementation("junit:junit:4.13.2")
        androidTestImplementation("androidx.test.ext:junit:1.1.3")
        androidTestImplementation("androidx.test.espresso:espresso-core:3.4.0")
    }
}