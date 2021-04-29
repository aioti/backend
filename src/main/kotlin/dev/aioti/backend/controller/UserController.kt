package dev.aioti.backend.controller

import dev.aioti.backend.dto.request.UserRegisterRequestDTO
import dev.aioti.backend.entity.User
import dev.aioti.backend.service.UserService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1/user")
class UserController(
    val service: UserService
) {


    @PostMapping
    fun register(@RequestBody userRequestDTO: UserRegisterRequestDTO): ResponseEntity<User> {
        return ResponseEntity(service.register(userRequestDTO), HttpStatus.CREATED)
    }

}
