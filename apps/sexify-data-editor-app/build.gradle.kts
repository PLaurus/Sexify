import org.jetbrains.compose.compose
import org.jetbrains.compose.desktop.application.dsl.TargetFormat
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm")
    kotlin("kapt")
    id("org.jetbrains.compose")
}

group = "com.lauruscorp"
version = "1.0"

dependencies {
    // Modules
    api(project(":core-jvm"))
    implementation(project(":sexify-data"))

    // Asynchronous programming
    // https://github.com/Kotlin/kotlinx.coroutines/releases
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.4")

    // Reflection
    // https://mvnrepository.com/artifact/org.jetbrains.kotlin/kotlin-reflect
    implementation("org.jetbrains.kotlin:kotlin-reflect:1.7.10")

    // Dependency injection
    // https://github.com/google/dagger/releases
    implementation("com.google.dagger:dagger:2.43.2")
    kapt("com.google.dagger:dagger-compiler:2.43.2")

    // MVI
    // https://github.com/arkivanov/MVIKotlin/releases/tag/3.0.2
    val mviKotlinVersion = "3.0.2"
    implementation("com.arkivanov.mvikotlin:mvikotlin:$mviKotlinVersion")
    implementation("com.arkivanov.mvikotlin:mvikotlin-main:$mviKotlinVersion")
    implementation("com.arkivanov.mvikotlin:mvikotlin-logging:$mviKotlinVersion")
    implementation("com.arkivanov.mvikotlin:mvikotlin-timetravel:$mviKotlinVersion")
    implementation("com.arkivanov.mvikotlin:mvikotlin-extensions-coroutines:$mviKotlinVersion")

    // Testing
    testImplementation("junit:junit:4.13.2")

    // UI
    implementation(compose.desktop.currentOs)
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "11"
}

compose.desktop {
    application {
        mainClass = "application.presentation.SexifyDataEditorAppKt"
        nativeDistributions {
            targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)
            packageName = "sexify-data-editor-app"
            packageVersion = "1.0.0"
        }
    }
}