package pl.kossa.nasa.app.server

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class NasaAppServerApplication

fun main(args: Array<String>) {
	runApplication<NasaAppServerApplication>(*args)
}
