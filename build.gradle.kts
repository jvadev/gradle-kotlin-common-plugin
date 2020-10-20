plugins {
    `kotlin-dsl`
    id("com.gradle.plugin-publish") version "0.11.0"
    //`maven-publish` // for debugging
}

group = "com.jvadev"

repositories {
    mavenCentral()
    gradlePluginPortal()
}

val kotlinVersion = "1.4.10"

dependencies {
    implementation(kotlin("stdlib-jdk8", kotlinVersion))
    implementation(kotlin("gradle-plugin", kotlinVersion))
    implementation(kotlin("allopen", kotlinVersion))
    implementation("org.jlleitschuh.gradle:ktlint-gradle:9.4.0")
    implementation("io.freefair.gradle:lombok-plugin:5.0.0-rc6")

    testImplementation("io.kotest:kotest-runner-junit5-jvm:4.2.3")
    testImplementation("io.kotest:kotest-assertions-core-jvm:4.2.3")
}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile>().configureEach {
    kotlinOptions.jvmTarget = "11"
    kotlinOptions.freeCompilerArgs = listOf("-Xjsr305=strict")
}

pluginBundle {
    website = "https://github.com/jvadev/gradle-kotlin-common-plugin"
    vcsUrl = "https://github.com/jvadev/gradle-kotlin-common-plugin.git"
    tags = listOf("kotlin", "ktlint")
}

gradlePlugin
    .plugins
    .find { it.name == "gradle-kotlin-common-plugin" }!!
    .apply {
        id = "com.jvadev.gradle-kotlin-common-plugin"
        displayName = "A plugin that sets up kotlin and checks code style in your project"
        description = "A plugin that sets up kotlin dependencies, plugins, build settings and checks official code style"
    }