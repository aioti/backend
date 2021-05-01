package dev.aioti.backend.exception

import org.springframework.http.HttpStatus

class NotFoundException(
    override val title: String? = null,
    override val detail: String? = null
) : AiotiException() {
    override val status: HttpStatus = HttpStatus.NOT_FOUND
}
