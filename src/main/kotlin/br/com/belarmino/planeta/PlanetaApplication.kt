package br.com.belarmino.planeta

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class PlanetaApplication

fun main(args: Array<String>) {
	runApplication<PlanetaApplication>(*args)
}
