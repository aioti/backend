package dev.aioti.backend.dto.response

import dev.aioti.backend.entity.User

interface HouseResponseDTO {
    val id: Long
    val name: String
    val location: String
    val user: User
}
