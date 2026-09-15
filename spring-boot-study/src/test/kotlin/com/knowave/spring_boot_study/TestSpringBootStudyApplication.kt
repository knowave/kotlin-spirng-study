package com.knowave.spring_boot_study

import org.springframework.boot.fromApplication
import org.springframework.boot.with


fun main(args: Array<String>) {
	fromApplication<SpringBootStudyApplication>().with(TestcontainersConfiguration::class).run(*args)
}
