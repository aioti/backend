package dev.aioti.backend.service

import dev.aioti.backend.dto.request.DeviceTypeRegisterDTO
import dev.aioti.backend.entity.DeviceCategory
import dev.aioti.backend.respository.DeviceTypeRepository
import javassist.NotFoundException
import org.springframework.stereotype.Service

@Service
class DeviceTypeService(
    val deviceTypeRepository: DeviceTypeRepository
) {
    fun create(deviceTypeRegisterDTO: DeviceTypeRegisterDTO) =
        deviceTypeRepository.save(DeviceCategory(deviceTypeRegisterDTO))

    fun update(id: Long, requestDTO: DeviceTypeRegisterDTO): DeviceCategory {
        val deviceResult = deviceTypeRepository.findById(id)

        if (deviceResult.isEmpty)
            throw NotFoundException("Tipo de dispositivo não encontrado")

        val device = deviceResult.get()

        device.name = requestDTO.name
        device.description = requestDTO.description

        return deviceTypeRepository.save(device)
    }

    fun deviceTypes(): List<DeviceCategory?> = deviceTypeRepository.findAll()
}
