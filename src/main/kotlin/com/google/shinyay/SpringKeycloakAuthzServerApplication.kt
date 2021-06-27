package com.google.shinyay

import com.google.shinyay.config.KeycloakServerProperties
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.web.ServerProperties
import org.springframework.boot.context.event.ApplicationReadyEvent
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.boot.runApplication
import org.springframework.context.ApplicationListener
import org.springframework.context.annotation.Bean


@SpringBootApplication
@ConfigurationPropertiesScan
class SpringKeycloakAuthzServerApplication

fun main(args: Array<String>) {
	runApplication<SpringKeycloakAuthzServerApplication>(*args)
}

val Any.logger: Logger
	get() = LoggerFactory.getLogger(this.javaClass)