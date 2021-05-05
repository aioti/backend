package dev.aioti.backend.service

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthException
import com.google.firebase.auth.FirebaseToken
import dev.aioti.backend.entity.User
import dev.aioti.backend.exception.UnauthorizedException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class FirebaseService {

    @Autowired
    lateinit var userService: UserService

    fun verifyToken(token: String): FirebaseToken {
        try {
            val firebase = FirebaseAuth.getInstance()
            return firebase.verifyIdToken(token)
                ?: throw UnauthorizedException("Token não é válido")
        } catch (exception: FirebaseAuthException) {
            throw UnauthorizedException("Token não é válido")
        }
    }

    fun retrieveUserFromStringToken(token: String): User {
        val verifiedToken = verifyToken(token)
        return userService.getUserByEmail(verifiedToken.email)
            ?: throw UnauthorizedException("Usuário não encontrado")
    }
}
