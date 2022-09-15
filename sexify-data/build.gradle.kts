plugins {
	kotlin("multiplatform")
	id("com.android.library")
	id("com.squareup.sqldelight")
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
			baseName = "sexify-data"
		}
	}
	
	android()
	
	sourceSets {
		val commonMain by getting {
			dependencies {
				implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.4")
				implementation("com.squareup.sqldelight:runtime:1.5.3")
				implementation("com.squareup.sqldelight:coroutines-extensions:1.5.3")
			}
		}
		
		val commonTest by getting {
			dependencies {
				implementation(kotlin("test"))
			}
		}
		
		val jvmMain by getting {
			dependencies {
				implementation("com.squareup.sqldelight:sqlite-driver:1.5.3")
			}
		}
		
		val nativeMain by getting {
			dependencies {
				implementation("co.touchlab:sqliter-driver:1.2.1")
				implementation("com.squareup.sqldelight:native-driver:1.5.3")
			}
		}
		
		val iosX64Main by getting
		val iosArm32Main by getting
		val iosArm64Main by getting
		val iosSimulatorArm64Main by getting
		
		val androidMain by getting {
			dependencies {
				// Implementation of the AndroidX SQLite interfaces via the Android framework APIs.
				implementation("androidx.sqlite:sqlite-framework:2.2.0")
				implementation("com.squareup.sqldelight:android-driver:1.5.3")
			}
		}
		
		val iosMain by creating {
			dependencies {
				implementation("com.squareup.sqldelight:native-driver:1.5.3")
			}
			
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
		minSdk = 24
		targetSdk = 33
	}
	
	compileOptions {
		sourceCompatibility = JavaVersion.VERSION_11
		targetCompatibility = JavaVersion.VERSION_11
	}
}

sqldelight {
	database("SexifyDatabase") {
		packageName = "com.lauruscorp.sexify_data.database"
		schemaOutputDirectory = file("src/main/sqldelight/databases")
	}
}