package com.google.shinyay.config

import org.jboss.resteasy.plugins.server.servlet.HttpServlet30Dispatcher
import org.jboss.resteasy.plugins.server.servlet.ResteasyContextParameters
import org.springframework.boot.web.servlet.FilterRegistrationBean
import org.springframework.boot.web.servlet.ServletRegistrationBean
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import javax.naming.CompositeName
import javax.naming.InitialContext
import javax.naming.Name
import javax.naming.NameParser
import javax.naming.spi.InitialContextFactory
import javax.naming.spi.NamingManager
import javax.sql.DataSource


@Configuration
class EmbeddedKeycloakConfiguration {

    @Bean
    fun keycloakJaxRsRegistration(
        properties: KeycloakServerProperties,
        datasource: DataSource
    ): ServletRegistrationBean<HttpServlet30Dispatcher> {
        NamingManager.setInitialContextFactoryBuilder { env ->
            InitialContextFactory { environment ->
                object : InitialContext() {
                    override fun lookup(name: Name): Any? {
                        return lookup(name.toString())
                    }

                    override fun lookup(name: String): Any? {
                        if ("spring/datasource" == name) {
                            return datasource
                        }
                        return null
                    }

                    override fun getNameParser(name: String): NameParser {
                        return NameParser { n: String? -> CompositeName(n) }
                    }

                    override fun close() {}
                }
            }
        }

        EmbeddedKeycloakApplication.keycloakServerProperties
        val servlet = ServletRegistrationBean(HttpServlet30Dispatcher()).apply {
            addInitParameter("javax.ws.rs.Application", EmbeddedKeycloakApplication::class.java.name)
            addInitParameter(
                ResteasyContextParameters.RESTEASY_SERVLET_MAPPING_PREFIX,
                properties.contextPath
            )
            addInitParameter(ResteasyContextParameters.RESTEASY_USE_CONTAINER_FORM_PARAMS, "true")
            addUrlMappings(properties.contextPath + "/*")
            setLoadOnStartup(1)
        }
        servlet.isAsyncSupported = true

        return servlet
    }

    @Bean
    fun keycloakSessionManagement(keycloakServerProperties: KeycloakServerProperties): FilterRegistrationBean<EmbeddedKeycloakRequestFilter>? {
        val filter: FilterRegistrationBean<EmbeddedKeycloakRequestFilter> =
            FilterRegistrationBean<EmbeddedKeycloakRequestFilter>()
        filter.setName("Keycloak Session Management")
        filter.filter = EmbeddedKeycloakRequestFilter()
        filter.addUrlPatterns(keycloakServerProperties.contextPath + "/*")
        return filter
    }
}