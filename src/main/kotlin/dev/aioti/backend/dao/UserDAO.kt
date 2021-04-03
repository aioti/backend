package dev.aioti.backend.dao

import dev.aioti.backend.entity.User
import dev.aioti.backend.utils.HibernateUtils
import org.springframework.stereotype.Component

@Component
class UserDAO {
    fun insert(user: User): User {
        val em = HibernateUtils.createEntityManager()

        try {
            em.transaction.begin()
            em.persist(user)
            em.transaction.commit()
            return user
        } catch (e: Exception) {
            em.transaction.rollback()
            throw e
        } finally {
            em.close()
        }
    }
}
