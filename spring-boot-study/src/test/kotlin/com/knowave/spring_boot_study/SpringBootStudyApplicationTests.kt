package com.knowave.spring_boot_study

import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.annotation.Import

@Import(TestcontainersConfiguration::class)
@SpringBootTest
class SpringBootStudyApplicationTests {

	@Test
	fun contextLoads() {
	}

}
