package dev.aioti.backend.dto.response

import org.springframework.http.HttpStatus

data class ProblemDetailResponseDTO(
    val status: HttpStatus,
    val title: String? = null,
    val detail: String? = null
)
