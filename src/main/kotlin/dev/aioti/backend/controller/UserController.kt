package dev.aioti.backend.controller

import dev.aioti.backend.dto.request.UserRegisterRequestDTO
import dev.aioti.backend.service.UserService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1/user")
class UserController(
    val service: UserService
) {

    @PostMapping("/register")
    fun register(@RequestBody userRequestDTO: UserRegisterRequestDTO, @RequestHeader authorization: String) =
        ResponseEntity(service.register(userRequestDTO, authorization), HttpStatus.CREATED)

}
