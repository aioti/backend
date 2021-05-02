package dev.aioti.backend.exception

import dev.aioti.backend.dto.response.ProblemDetailResponseDTO
import org.springframework.http.HttpStatus

abstract class AiotiException(message: String? = null): Exception(message), ProblemDetail {
    abstract override val status: HttpStatus
    abstract val title: String?
    abstract val detail: String?

    override fun getResponseDTO() = ProblemDetailResponseDTO(status, title, detail)
}
