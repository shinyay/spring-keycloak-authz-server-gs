package com.google.shinyay.provider

import org.keycloak.platform.PlatformProvider
import org.keycloak.services.ServicesLogger
import java.io.File
import kotlin.system.exitProcess

class SimplePlatformProvider : PlatformProvider {
    var tmpDir: File? = null
    var onStartup: Runnable? = null
    var onShutdown: Runnable? = null

    override fun onStartup(startup: Runnable?) {
        startup?.run()
    }

    override fun onShutdown(shutdown: Runnable?) {
        shutdown?.run()
    }

    override fun exit(cause: Throwable?) {
        ServicesLogger.LOGGER.fatal(cause)
        object : Thread() {
            override fun run() {
                exitProcess(1)
            }
        }.start()
    }

    override fun getTmpDirectory(): File? = tmpDir
}