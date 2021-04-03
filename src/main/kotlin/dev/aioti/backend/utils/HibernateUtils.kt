package dev.aioti.backend.utils

import javax.persistence.EntityManager
import javax.persistence.EntityManagerFactory
import javax.persistence.Persistence

class HibernateUtils {
    companion object {
        private const val persistenceUnitName = "aioti"
        private var AIOTI_ENTITY_MANAGER_FACTORY: EntityManagerFactory? = null

        fun createEntityManager(): EntityManager {
            if (AIOTI_ENTITY_MANAGER_FACTORY == null) {
                generateEntityManagerFactory()
            }
            return AIOTI_ENTITY_MANAGER_FACTORY!!.createEntityManager()
        }

        private fun generateEntityManagerFactory() {
            AIOTI_ENTITY_MANAGER_FACTORY = Persistence.createEntityManagerFactory(persistenceUnitName)
        }
    }
}
