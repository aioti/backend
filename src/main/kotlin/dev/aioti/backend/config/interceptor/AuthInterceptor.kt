package dev.aioti.backend.config.interceptor

import dev.aioti.backend.dto.CurrentUserDTO
import dev.aioti.backend.entity.User
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import org.springframework.web.servlet.HandlerInterceptor
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Component
class AuthInterceptor: HandlerInterceptor {

    @Autowired
    lateinit var currentUserDTO: CurrentUserDTO

    override fun preHandle(request: HttpServletRequest, response: HttpServletResponse, handler: Any): Boolean {

        currentUserDTO.user = User(
            22,
            "Gabriel Sancho",
            "gabriel.sancho13@gmail.com",
            null,
            null
        )

        return super.preHandle(request, response, handler)
    }

}
