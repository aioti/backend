package dev.aioti.backend.exception

import dev.aioti.backend.dto.response.ProblemDetailResponseDTO
import org.springframework.http.HttpStatus

interface ProblemDetail {
    val status: HttpStatus

    fun getResponseDTO() = ProblemDetailResponseDTO(status)
}
