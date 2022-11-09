package pl.kossa.nasa.app.server.restcontrollers

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class HelloRestController {

    @GetMapping("/hello")
    fun hello(): String {
        return "Hello"
    }
}