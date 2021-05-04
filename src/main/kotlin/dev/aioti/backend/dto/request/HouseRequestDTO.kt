package dev.aioti.backend.dto.request

data class HouseRequestDTO(
    val name: String,
    val location: String? = null
)
