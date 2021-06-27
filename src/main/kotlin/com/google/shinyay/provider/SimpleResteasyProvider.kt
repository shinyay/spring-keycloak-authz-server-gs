package com.google.shinyay.provider

import org.jboss.resteasy.spi.ResteasyProviderFactory
import org.keycloak.common.util.ResteasyProvider

class SimpleResteasyProvider : ResteasyProvider {
    override fun <R : Any?> getContextData(type: Class<R>?): R {
        ResteasyProviderFactory.getInstance()
        return ResteasyProviderFactory.getContextData(type)
    }

    override fun pushDefaultContextObject(type: Class<*>?, instance: Any?) {
        TODO("Not yet implemented")
    }

    override fun pushContext(type: Class<*>?, instance: Any?) {
        TODO("Not yet implemented")
    }

    override fun clearContextData() {
        TODO("Not yet implemented")
    }
}