package dev.aioti.backend.service

import dev.aioti.backend.dto.request.DeviceCategoryRegisterDTO
import dev.aioti.backend.entity.DeviceCategory
import dev.aioti.backend.respository.DeviceCategoryRepository
import javassist.NotFoundException
import org.springframework.stereotype.Service

@Service
class DeviceCategoryService(
    val deviceCategoryRepository: DeviceCategoryRepository
) {
    fun create(deviceCategoryRegisterDTO: DeviceCategoryRegisterDTO) =
        deviceCategoryRepository.save(DeviceCategory(deviceCategoryRegisterDTO))

    fun update(id: Long, requestDTO: DeviceCategoryRegisterDTO): DeviceCategory {
        val deviceResult = deviceCategoryRepository.findById(id)

        if (deviceResult.isEmpty)
            throw NotFoundException("Tipo de dispositivo não encontrado")

        val device = deviceResult.get()

        device.name = requestDTO.name
        device.description = requestDTO.description

        return deviceCategoryRepository.save(device)
    }

    fun deviceTypes(): List<DeviceCategory?> = deviceCategoryRepository.findAll()
    fun deviceByName(name: String) = deviceCategoryRepository.findByName(name)
}
