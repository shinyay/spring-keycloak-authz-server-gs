package com.google.shinyay.config

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties(prefix = "keycloak.server")
class KeycloakServerProperties {
}