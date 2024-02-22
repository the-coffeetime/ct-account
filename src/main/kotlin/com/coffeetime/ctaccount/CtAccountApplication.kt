package com.coffeetime.ctaccount

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
//@EnableConfigurationProperties(BlogProperties::class)
class CtAccountApplication

fun main(args: Array<String>) {
	runApplication<CtAccountApplication>(*args)
}
