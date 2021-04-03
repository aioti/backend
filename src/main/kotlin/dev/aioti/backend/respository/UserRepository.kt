package dev.aioti.backend.respository

import dev.aioti.backend.dao.UserDAO
import dev.aioti.backend.entity.User
import org.springframework.stereotype.Repository

@Repository
class UserRepository(
    val dao: UserDAO
) {
    fun register(user: User): User = dao.insert(user)
}
