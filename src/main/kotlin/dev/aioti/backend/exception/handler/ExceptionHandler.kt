package dev.aioti.backend.exception.handler

import dev.aioti.backend.dto.response.ProblemDetailResponseDTO
import dev.aioti.backend.exception.AiotiException
import dev.aioti.backend.exception.NotFoundException
import dev.aioti.backend.exception.UnauthorizedException
import org.hibernate.engine.jdbc.spi.SqlExceptionHelper
import org.springframework.core.Ordered
import org.springframework.core.annotation.Order
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler
import java.sql.SQLException
import javax.print.attribute.standard.Media

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
class ExceptionHandler : ResponseEntityExceptionHandler() {

    @ExceptionHandler(
        value = [
            NotFoundException::class,
            UnauthorizedException::class
        ]
    )
    fun handle(exception: AiotiException) = ResponseEntity
        .status(exception.status)
        .contentType(MediaType.APPLICATION_PROBLEM_JSON)
        .body(exception.getResponseDTO())

    @ExceptionHandler(SQLException::class)
    fun handle(exception: SQLException) = ResponseEntity
        .status(HttpStatus.INTERNAL_SERVER_ERROR)
        .contentType(MediaType.APPLICATION_PROBLEM_JSON)
        .body(
            ProblemDetailResponseDTO(
                HttpStatus.INTERNAL_SERVER_ERROR, "SQL Error", exception.message
            )
        )
}
