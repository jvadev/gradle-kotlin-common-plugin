[![Build](https://github.com/jvadev/gradle-kotlin-common-plugin/workflows/Build/badge.svg)](https://github.com/jvadev/gradle-kotlin-common-plugin/actions?query=workflow%3ABuild+branch%3Amaster)
[![Gradle Plugin Portal](https://img.shields.io/maven-metadata/v?label=Gradle%20Plugin%20Portal&logo=gradle&logoColor=blue&metadataUrl=https%3A%2F%2Fplugins.gradle.org%2Fm2%2Fcom%2Fjvadev%2Fgradle-kotlin-common-plugin%2Fcom.jvadev.gradle-kotlin-common-plugin.gradle.plugin%2Fmaven-metadata.xml)](https://plugins.gradle.org/plugin/com.jvadev.gradle-kotlin-common-plugin)
[![semantic-release](https://img.shields.io/badge/%20%20%F0%9F%93%A6%F0%9F%9A%80-semantic--release-e10079.svg)](https://github.com/jvadev/gradle-kotlin-common-plugin/actions?query=workflow%3ARelease)
[![License](https://img.shields.io/github/license/jvadev/gradle-kotlin-common-plugin)](https://github.com/jvadev/gradle-kotlin-common-plugin/blob/master/LICENSE)

# Gradle Kotlin Common Plugin

The plugin embraces all necessary libraries to start a new Kotlin project as well as to introduce Kotlin into an existing Java one. 

The plugin performs the following :

1. Applies the `kotlin-jvm` and `KAPT` gradle plugins
2. Adds common Kotlin dependencies, such as:
    - `kotlin std lib` (added by the `kotlin-jvm` plugin)
    - `kotlin-reflect`
    - `kotlin-coroutines`
    - [`ktlint`](https://github.com/pinterest/ktlint)
    - [`kalidation`](https://github.com/rcapraro/kalidation)
    - [`kotest` (with extensions)](https://github.com/kotest/kotest)
    - [`mockk`](https://github.com/mockk/mockk)
    - [`mockito-kotlin`](https://github.com/nhaarman/mockito-kotlin)
    - and [others](https://github.com/jvadev/gradle-kotlin-common-plugin/blob/master/src/main/kotlin/com/jvadev/gradle-kotlin-common-plugin.gradle.kts)
3. Configures `kotlinOptions.jvmTarget` to match the `JavaPlugin.sourceCompatibility`
4. Resolves the Kotlin's [incompatibility](https://stackoverflow.com/a/35530223/2441104) with Lombok by delomboking
the project's java source code and pointing the `JavaPlugin` compile tasks to the delomboked source code files.

## Distribution

The [plugin](https://plugins.gradle.org/plugin/com.jvadev.gradle-kotlin-common-plugin) is available on the Gradle Plugins portal.

## Getting Started

### Requirements

Gradle >= `v. 6.0` is required to use this plugin, because it is a
[precompiled script plugin](https://docs.gradle.org/current/userguide/custom_plugins.html#sec:precompiled_plugins)
built with Gradle >= `v. 6.0`.

### Install

#### Groovy DSL

Add this to your project's `build.gradle`:

```
plugins {
  id "com.jvadev.gradle-kotlin-common-plugin" version "2.0.0"
}
```

#### Kotlin DSL

Add this to your project's `build.gradle.kts`:

```
plugins {
  id("com.jvadev.gradle-kotlin-common-plugin") version "2.0.0"
}
```

## Development

### Prerequisites

- [Kotlin DSL Plugin](https://docs.gradle.org/current/userguide/kotlin_dsl.html#sec:kotlin-dsl_plugin),
- [JDK](https://stackoverflow.com/a/52524114/2441104), preferably >= `v. 1.8`

### Build

```
./gradlew clean build
```