package dev.aioti.backend.controller

import dev.aioti.backend.dto.UserRegisterDTO
import dev.aioti.backend.service.UserService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1/user")
class UserController(
    val service: UserService
) {


    @PostMapping
    fun register(userDTO: UserRegisterDTO) {
        service.register(userDTO)
    }

}
