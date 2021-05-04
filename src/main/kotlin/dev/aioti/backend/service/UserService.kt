package dev.aioti.backend.service

import dev.aioti.backend.dto.request.UserRegisterRequestDTO
import dev.aioti.backend.entity.User
import dev.aioti.backend.respository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class UserService(
    private val repository: UserRepository
) {

    @Autowired
    lateinit var firebaseService: FirebaseService

    fun register(userRequestDTO: UserRegisterRequestDTO, authorization: String): User {
        val token = authorization.substring("Bearer ".length)
        val firebaseToken = firebaseService.verifyToken(token)

        val user = User(firebaseToken)

        user.email = userRequestDTO.email
        user.name = userRequestDTO.name

        return repository.save(user)
    }

    fun getUserByEmail(email: String) = repository.findByEmail(email)
}
