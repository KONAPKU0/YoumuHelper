plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("kotlin-parcelize")
    kotlin("plugin.serialization")
    id("maven-publish")
}

android {
    namespace = "ml.youmu.helper"
    compileSdk = 34

    defaultConfig {
        minSdk = 24

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        release {
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
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        compose = true
        buildConfig = true
    }
    composeOptions {
        //https://developer.android.com/jetpack/androidx/releases/compose-kotlin
        kotlinCompilerExtensionVersion = "1.5.1"
    }
}

val GROUP_ID = "com.github.youmu"
val ARTIFACT_ID = "YoumuHelper"
val VERSION = latestGitTag().ifEmpty { "1.0.0-SNAPSHOT" }

fun latestGitTag(): String {
    val process = ProcessBuilder("git", "describe", "--tags", "--abbrev=0").start()
    return  process.inputStream.bufferedReader().use {bufferedReader ->
        bufferedReader.readText().trim()
    }
}

publishing { // 发布配置
    publications { // 发布的内容
        register<MavenPublication>("release") { // 注册一个名字为 release 的发布内容
            groupId = GROUP_ID
            artifactId = ARTIFACT_ID
            version = VERSION

            afterEvaluate { // 在所有的配置都完成之后执行
                // 从当前 module 的 release 包中发布
                from(components["release"])
            }
        }
    }
}



dependencies {

    debugApi("com.github.chuckerteam.chucker:library:4.0.0")
    releaseApi("com.github.chuckerteam.chucker:library-no-op:4.0.0")
    api("androidx.core:core-ktx:1.12.0")
    api("androidx.lifecycle:lifecycle-runtime-ktx:2.7.0")
    api("androidx.activity:activity-compose:1.8.2")
    api("androidx.startup:startup-runtime:1.2.0-alpha02")
    api("androidx.core:core-splashscreen:1.0.1")
    //https://developer.android.com/jetpack/compose/bom/bom-mapping
    api(platform("androidx.compose:compose-bom:2023.10.01"))
    api("androidx.compose.ui:ui")
    api("androidx.compose.ui:ui-graphics")
    api("androidx.compose.ui:ui-tooling-preview")
    api("androidx.compose.material3:material3")
    api("androidx.lifecycle:lifecycle-viewmodel-compose:2.7.0")
    api("androidx.lifecycle:lifecycle-runtime-compose:2.7.0")
    testApi("junit:junit:4.13.2")
    androidTestApi("androidx.test.ext:junit:1.1.5")
    androidTestApi("androidx.test.espresso:espresso-core:3.5.1")
    androidTestApi(platform("androidx.compose:compose-bom:2023.10.01"))
    androidTestApi("androidx.compose.ui:ui-test-junit4")
    debugApi("androidx.compose.ui:ui-tooling")
    debugApi("androidx.compose.ui:ui-test-manifest")

    //https://developer.android.com/jetpack/compose/navigation#kts
    api("androidx.navigation:navigation-compose:2.7.7")
    api("org.jetbrains.kotlinx:kotlinx-serialization-json:1.6.2")

    api("androidx.paging:paging-compose:3.2.1")
    api("androidx.paging:paging-runtime-ktx:3.2.1")

    //https://github.com/google/accompanist
    api("com.google.accompanist:accompanist-systemuicontroller:0.32.0")


    //https://github.com/workspace/bottomsheetdialog-compose
    api("com.holix.android:bottomsheetdialog-compose:1.3.1")
    //https://github.com/liangjingkanji/LogCat
    api("com.github.liangjingkanji:LogCat:1.2.4")


    //https://github.com/FunnySaltyFish/ComposeDataSaver?tab=readme-ov-file
    api("com.github.FunnySaltyFish.ComposeDataSaver:data-saver:1.1.9")
    api("com.github.FunnySaltyFish.ComposeDataSaver:data-saver-mmkv:1.1.9")
    api("com.tencent:mmkv:1.3.0")

    //https://github.com/getActivity/Toaster
    api("com.github.getActivity:Toaster:12.6")


    // 权限请求框架：https://github.com/getActivity/XXPermissions
    api("com.github.getActivity:XXPermissions:18.5")

    //https://github.com/valentinilk/compose-shimmer
    api("com.valentinilk.shimmer:compose-shimmer:1.2.0")

    //https://github.com/KevinnZou/compose-pagingList
    api("io.github.kevinnzou:compose-paginglist:1.1.0")


    api("com.github.liangjingkanji:Channel:1.1.5")
    api("io.coil-kt:coil-compose:2.4.0")
    api("io.coil-kt:coil-gif:2.4.0")
}