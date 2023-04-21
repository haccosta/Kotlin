package com.costa.handerson.resfulcomkotlin

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cache.annotation.EnableCaching

@SpringBootApplication
@EnableCaching
class ResfulComKotlinApplication

fun main(args: Array<String>) {
	runApplication<ResfulComKotlinApplication>(*args)
}
