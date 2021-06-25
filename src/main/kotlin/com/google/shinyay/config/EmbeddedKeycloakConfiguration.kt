package com.google.shinyay.config

import org.jboss.resteasy.plugins.server.servlet.HttpServlet30Dispatcher
import org.springframework.boot.web.servlet.ServletRegistrationBean
import org.springframework.context.annotation.Configuration
import java.util.*
import javax.naming.*
import javax.naming.spi.InitialContextFactory
import javax.naming.spi.InitialContextFactoryBuilder
import javax.naming.spi.NamingManager
import javax.sql.DataSource


@Configuration
class EmbeddedKeycloakConfiguration {

    fun keycloakJaxRsRegistration(
        properties: KeycloakServerProperties,
        datasource: DataSource
    ) {
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
    }
}