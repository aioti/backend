package dev.aioti.backend.exception

import org.springframework.http.HttpStatus

class UnauthorizedException(
    override val detail: String? = null,
    override val title: String? = HttpStatus.UNAUTHORIZED.reasonPhrase
) : AiotiException() {
    override val status: HttpStatus = HttpStatus.UNAUTHORIZED
}
