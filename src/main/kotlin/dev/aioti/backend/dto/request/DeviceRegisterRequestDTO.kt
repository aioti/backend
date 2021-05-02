package dev.aioti.backend.dto.request

data class DeviceRegisterRequestDTO (
    val name: String,
    val category: DeviceTypeRegisterDTO,
    val house: HouseRequestDTO?
)
