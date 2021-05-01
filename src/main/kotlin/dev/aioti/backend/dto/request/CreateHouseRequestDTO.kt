package dev.aioti.backend.dto.request

data class CreateHouseRequestDTO(
    val name: String,
    val location: String? = null
)
