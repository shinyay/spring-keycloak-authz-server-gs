package com.google.shinyay.provider

import org.keycloak.platform.PlatformProvider
import java.io.File

class SimplePlatformProvider : PlatformProvider {
    override fun onStartup(runnable: Runnable?) {
        TODO("Not yet implemented")
    }

    override fun onShutdown(runnable: Runnable?) {
        TODO("Not yet implemented")
    }

    override fun exit(cause: Throwable?) {
        TODO("Not yet implemented")
    }

    override fun getTmpDirectory(): File {
        TODO("Not yet implemented")
    }
}