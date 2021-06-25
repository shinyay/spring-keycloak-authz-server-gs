package com.google.shinyay.config

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties(prefix = "keycloak.server")
data class KeycloakServerProperties(
    val contextPath: String = "/auth",
    val realmImportFile: String = "realm.json",
    val adminUser: AdminUser = AdminUser()
)