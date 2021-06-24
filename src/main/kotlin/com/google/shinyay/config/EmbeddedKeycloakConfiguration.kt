package com.google.shinyay.config

import org.jboss.resteasy.plugins.server.servlet.HttpServlet30Dispatcher
import org.springframework.boot.web.servlet.ServletRegistrationBean
import org.springframework.context.annotation.Configuration

@Configuration
class EmbeddedKeycloakConfiguration {

    fun keycloakJaxRsRegistration(): ServletRegistrationBean<HttpServlet30Dispatcher> {}
}