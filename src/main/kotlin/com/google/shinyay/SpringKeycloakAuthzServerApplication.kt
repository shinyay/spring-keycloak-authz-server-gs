package com.google.shinyay

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.boot.runApplication

@SpringBootApplication
@ConfigurationPropertiesScan
class SpringKeycloakAuthzServerApplication

fun main(args: Array<String>) {
	runApplication<SpringKeycloakAuthzServerApplication>(*args)
}
