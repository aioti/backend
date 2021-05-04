package dev.aioti.backend.exception

import org.springframework.http.HttpStatus

class BadRequestException(
    override val title: String? = null,
    override val detail: String? = null
) : AiotiException(title) {
    override val status: HttpStatus = HttpStatus.BAD_REQUEST
}
