package dev.aioti.backend.service

import dev.aioti.backend.dto.UserRegisterDTO
import dev.aioti.backend.entity.User
import dev.aioti.backend.respository.UserRepository
import org.springframework.stereotype.Service

@Service
class UserService(
    val repository: UserRepository
) {

    fun register(userDTO: UserRegisterDTO): User {
        return repository.register(
            User(
                null,
                userDTO.name,
                userDTO.email,
                userDTO.password
            )
        )
    }

}
