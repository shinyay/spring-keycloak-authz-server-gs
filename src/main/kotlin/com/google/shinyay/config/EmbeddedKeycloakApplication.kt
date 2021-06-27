package com.google.shinyay.config

import com.google.shinyay.logger
import org.keycloak.Config
import org.keycloak.representations.idm.RealmRepresentation
import org.keycloak.services.managers.ApplianceBootstrap
import org.keycloak.services.managers.RealmManager
import org.keycloak.services.resources.KeycloakApplication
import org.keycloak.services.util.JsonConfigProviderFactory
import org.keycloak.util.JsonSerialization
import org.springframework.core.io.ClassPathResource
import org.springframework.core.io.Resource


class EmbeddedKeycloakApplication() : KeycloakApplication() {
    companion object {
        val keycloakServerProperties: KeycloakServerProperties
            get() {
                return keycloakServerProperties
            }
    }
    object  KeycloakJsonConfigProviderFactory :  JsonConfigProviderFactory()

    override fun loadConfig() {
        val factory = KeycloakJsonConfigProviderFactory
        Config.init(factory.create()
            .orElseThrow { NoSuchElementException("No value present") })
    }

    init {
        createMasterRealmAdminUser()
        createRealm()
    }

    private fun createMasterRealmAdminUser() {
        val session = getSessionFactory().create()
        val applianceBootstrap = ApplianceBootstrap(session)
        val (username, password) = keycloakServerProperties.adminUser
        try {
            session.transactionManager.begin()
            applianceBootstrap.createMasterRealmUser(username, password)
            session.transactionManager.commit()
        } catch (ex: Exception) {
            logger.warn("Couldn't create keycloak master admin user: {}", ex.message)
            session.transactionManager.rollback()
        }
        session.close()
    }

    private fun createRealm() {
        val session = getSessionFactory().create()
        try {
            session.transactionManager.begin()
            val manager = RealmManager(session)
            val lessonRealmImportFile: Resource = ClassPathResource(keycloakServerProperties.realmImportFile)
            manager.importRealm(
                JsonSerialization.readValue(lessonRealmImportFile.inputStream, RealmRepresentation::class.java)
            )
            session.transactionManager.commit()
        } catch (ex: Exception) {
            logger.warn("Failed to import Realm json file: {}", ex.message)
            session.transactionManager.rollback()
        }
        session.close()
    }
}