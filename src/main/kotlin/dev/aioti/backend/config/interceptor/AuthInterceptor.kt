package dev.aioti.backend.config.interceptor

import com.google.firebase.auth.FirebaseAuth
import dev.aioti.backend.dto.CurrentUserDTO
import dev.aioti.backend.entity.User
import dev.aioti.backend.exception.BadRequestException
import dev.aioti.backend.exception.UnauthorizedException
import dev.aioti.backend.service.FirebaseService
import dev.aioti.backend.service.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import org.springframework.web.servlet.HandlerInterceptor
import java.lang.Exception
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Component
class AuthInterceptor: HandlerInterceptor {

    @Autowired
    lateinit var currentUserDTO: CurrentUserDTO

    @Autowired
    lateinit var firebaseService: FirebaseService

    override fun preHandle(request: HttpServletRequest, response: HttpServletResponse, handler: Any): Boolean {

        val authorizationHeader = request.getHeader("Authorization")

        if (authorizationHeader.isNullOrBlank())
            throw BadRequestException("Autorização não fornecida")

        val token = authorizationHeader.substring("Bearer ".length)

        currentUserDTO.user = firebaseService.retrieveUserFromStringToken(token)

        return super.preHandle(request, response, handler)
    }
}
