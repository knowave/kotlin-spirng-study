package com.example.kotlin_spring_boiler_code

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import io.github.cdimascio.dotenv.dotenv

@SpringBootApplication
class KotlinSpringBoilerCodeApplication

fun main(args: Array<String>) {
	val dotenv = dotenv()

	dotenv.entries().forEach { entry ->
		System.setProperty(entry.key, entry.value)
	}

	runApplication<KotlinSpringBoilerCodeApplication>(*args)
}
