import io.freefair.gradle.plugins.lombok.tasks.Delombok
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm")
    kotlin("kapt")
    id("org.jetbrains.kotlin.plugin.spring")
    id("io.freefair.lombok")
    id("org.jlleitschuh.gradle.ktlint")
}

tasks.find { it.name == "generateLombokConfig" }?.enabled = false

val coroutinesVersion = "1.3.9"
val kotestVersion = "4.2.5"
val mapstructVersion = "1.4.1.Final"

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-reflect:1.4.10")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:$coroutinesVersion")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-jdk8:$coroutinesVersion")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:$coroutinesVersion")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-reactive:$coroutinesVersion")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-reactor:$coroutinesVersion")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-rx2:$coroutinesVersion")

    implementation("com.capraro:kalidation:1.5.0")

    implementation("org.mapstruct:mapstruct:$mapstructVersion")
    kapt("org.mapstruct:mapstruct-processor:$mapstructVersion")
    kaptTest("org.mapstruct:mapstruct-processor:${mapstructVersion}")
    implementation("com.github.pozo:mapstruct-kotlin:1.3.1.2")
    kapt("com.github.pozo:mapstruct-kotlin-processor:1.3.1.2")

    implementation("com.fasterxml.jackson.module:jackson-module-kotlin:2.11.2")
    implementation("com.fasterxml.jackson.datatype:jackson-datatype-jsr310:2.11.2")

    implementation("org.awaitility:awaitility-kotlin:4.0.3")

    testImplementation("org.jetbrains.kotlin:kotlin-test")
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit")
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit5")
    testImplementation("com.nhaarman.mockitokotlin2:mockito-kotlin:2.2.0")
    testImplementation("io.mockk:mockk:1.9")

    testImplementation("io.kotest:kotest-runner-junit5-jvm:$kotestVersion")
    testImplementation("io.kotest:kotest-assertions-core-jvm:$kotestVersion")
    testImplementation("io.kotest:kotest-property-jvm:$kotestVersion")
    testImplementation("io.kotest:kotest-extensions-spring:$kotestVersion")
}

tasks.test { useJUnitPlatform() }

ktlint {
    disabledRules.set(setOf("import-ordering", "indent"))
}

tasks.withType(KotlinCompile::class.java).configureEach {
    kotlinOptions.jvmTarget = project.convention.getPlugin<JavaPluginConvention>().sourceCompatibility.ordinal
        .let {
            val version = it + 1
            return@let if (version < 9) "1.$version" else "$version"
        }
    kotlinOptions.freeCompilerArgs = listOf("-Xjsr305=strict")
}

tasks.filter { listOf("compileJava", "compileTestJava").contains(it.name) }
    .filter { it.enabled }
    .map { it as JavaCompile }
    .filter { it.source.asFileTree.files.isNotEmpty() }
    .forEach { task ->
        task.source = project.properties["delombok"].let { it as Delombok }.target.asFileTree
    }