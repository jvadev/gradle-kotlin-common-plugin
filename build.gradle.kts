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
    implementation("org.springframework.boot:spring-boot-gradle-plugin:2.2.6.RELEASE")
    implementation("io.spring.gradle:dependency-management-plugin:1.0.9.RELEASE")
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