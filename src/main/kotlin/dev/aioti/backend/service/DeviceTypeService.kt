package dev.aioti.backend.service

import dev.aioti.backend.dto.request.DeviceTypeRegisterDTO
import dev.aioti.backend.entity.DeviceType
import dev.aioti.backend.respository.DeviceTypeRepository
import javassist.NotFoundException
import org.springframework.stereotype.Service

@Service
class DeviceTypeService(
    val deviceTypeRepository: DeviceTypeRepository
) {
    fun create(deviceTypeRegisterDTO: DeviceTypeRegisterDTO) =
        deviceTypeRepository.save(DeviceType(deviceTypeRegisterDTO))

    fun update(id: Long, requestDTO: DeviceTypeRegisterDTO): DeviceType {
        val deviceResult = deviceTypeRepository.findById(id)

        if (deviceResult.isEmpty)
            throw NotFoundException("Tipo de dispositivo não encontrado")

        val device = deviceResult.get()

        device.name = requestDTO.name
        device.description = requestDTO.description

        return deviceTypeRepository.save(device)
    }

    fun deviceTypes(): List<DeviceType?> = deviceTypeRepository.findAll()
}
