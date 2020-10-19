package com.jvadev

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.string.shouldContainIgnoringCase
import org.gradle.testkit.runner.BuildResult
import org.gradle.testkit.runner.GradleRunner
import org.gradle.testkit.runner.TaskOutcome

class GradleKotlinCommonPluginTest : FunSpec() {
    private val projectDirectory = createTempDir().apply {
        resolve("settings.gradle.kts").apply {
            appendText("rootProject.name = \"gradle-kotlin-common-plugin\"\n")
        }

        resolve("build.gradle.kts").apply {
            appendText(
                """
                    plugins {
                        id("com.jvadev.gradle-kotlin-common-plugin")
                    }
                    repositories {
                        mavenCentral()
                    }
            """
            )
        }
    }

    init {

        test("build") {
            val actual = buildResult("build")

            actual.output shouldContainIgnoringCase "kotlin"
            actual.assertSuccess(":build")
        }

        test("dependencies") {
            val actual = buildResult("dependencies")

            actual.output shouldContainIgnoringCase "org.jetbrains.kotlin"
            actual.output shouldContainIgnoringCase "kotest"
            actual.output shouldContainIgnoringCase "mockk"
            actual.output shouldContainIgnoringCase "kalidation"
            actual.assertSuccess(":dependencies")
        }

        test("tasks") {
            val actual = buildResult("tasks")

            actual.output shouldContainIgnoringCase "Build tasks"
            actual.output shouldContainIgnoringCase "Lombok tasks"
            actual.assertSuccess(":tasks")
        }

    }

    private fun BuildResult.assertSuccess(task: String) {
        task(task)?.outcome shouldBe TaskOutcome.SUCCESS
        output.contains("BUILD SUCCESSFUL")
    }

    private fun buildResult(vararg args: String): BuildResult =
        GradleRunner.create()
            .withProjectDir(projectDirectory)
            .withArguments(*args)
            .withPluginClasspath()
            .build()
}