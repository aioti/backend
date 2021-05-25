package dev.aioti.backend.dto.request

data class DeviceRegisterRequestDTO (
    val name: String,
    val category: DeviceCategoryRegisterDTO,
    val uuid: String,
    val house: HouseRequestDTO?
)
