package com.example.coffee_order

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.jpa.repository.config.EnableJpaAuditing
import io.github.cdimascio.dotenv.dotenv

@EnableJpaAuditing
@SpringBootApplication
class CoffeeOrderApplication

fun main(args: Array<String>) {
	val dotenv = dotenv()

	dotenv.entries().forEach { entry ->
		System.setProperty(entry.key, entry.value)
	}

	runApplication<CoffeeOrderApplication>(*args)
}
