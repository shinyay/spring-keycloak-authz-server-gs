package com.google.shinyay.provider

import org.keycloak.platform.PlatformProvider
import org.keycloak.services.ServicesLogger
import java.io.File
import kotlin.system.exitProcess

class SimplePlatformProvider : PlatformProvider {

    override fun onStartup(startup: Runnable?) {
        startup?.run()
    }

    override fun onShutdown(shutdown: Runnable?) {
    }

    override fun exit(cause: Throwable?) {

    }

    override fun getTmpDirectory(): File {
    }
}