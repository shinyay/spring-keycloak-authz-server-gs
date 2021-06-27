package com.google.shinyay.provider

import org.jboss.resteasy.core.Dispatcher
import org.jboss.resteasy.spi.ResteasyProviderFactory
import org.keycloak.common.util.ResteasyProvider
import kotlin.reflect.jvm.internal.impl.resolve.calls.inference.CapturedType

class SimpleResteasyProvider : ResteasyProvider {
    override fun <R : Any?> getContextData(type: Class<R>?): R {
        ResteasyProviderFactory.getInstance()
        return ResteasyProviderFactory.getContextData(type)
    }

    override fun pushDefaultContextObject(type: Class<*>?, instance: Any?) {
        ResteasyProviderFactory.getInstance()
        ResteasyProviderFactory.getContextData(Dispatcher::class.java).defaultContextObjects[type] = instance
    }

    override fun pushContext(type: Class<*>?, instance: Any) {
    }

    override fun clearContextData() {
        ResteasyProviderFactory.getInstance();
        ResteasyProviderFactory.clearContextData();
    }
}