package com.example.kotlin_spring_security

import io.github.cdimascio.dotenv.dotenv
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class KotlinSpringSecurityApplication

fun main(args: Array<String>) {
	val dotenv = dotenv()

	dotenv.entries().forEach { entry ->
		System.setProperty(entry.key, entry.value)
	}

	runApplication<KotlinSpringSecurityApplication>(*args)
}
