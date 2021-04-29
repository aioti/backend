package dev.aioti.backend.service

import dev.aioti.backend.dto.request.UserRegisterRequestDTO
import dev.aioti.backend.entity.User
import dev.aioti.backend.respository.UserRepository
import org.springframework.stereotype.Service

@Service
class UserService(
    val repository: UserRepository
) {

    fun register(userRequestDTO: UserRegisterRequestDTO): User {
        val user = User(userRequestDTO)
        return repository.save(user)
    }
}
