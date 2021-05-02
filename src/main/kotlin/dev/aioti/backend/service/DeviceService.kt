package dev.aioti.backend.service

import dev.aioti.backend.dto.CurrentUserDTO
import dev.aioti.backend.dto.request.DeviceRegisterRequestDTO
import dev.aioti.backend.entity.Device
import dev.aioti.backend.exception.NotFoundException
import dev.aioti.backend.respository.DeviceRepository
import dev.aioti.backend.respository.DeviceCategoryRepository
import org.springframework.stereotype.Service
import java.util.*

@Service
class DeviceService(
    val deviceRepository: DeviceRepository,
    val categoryRepository: DeviceCategoryRepository,
    private val currentUserDTO: CurrentUserDTO
) {
    fun create(requestDTO: DeviceRegisterRequestDTO): Device {

        val category = requestDTO.category.id?.let { categoryRepository.findById(it) }?.get()
            ?: throw NotFoundException("Categoria não encontrada")

        val device = Device(
            null,
            requestDTO.name,
            category,
            currentUserDTO.user,
            UUID.randomUUID().toString(),
            null
        )

        return deviceRepository.save(device)
    }


    fun device(id: Long) = deviceRepository.findById(id)
    fun devices() = deviceRepository.findAllByUser(currentUserDTO.user)

    fun update(id: Long, requestDTO: DeviceRegisterRequestDTO): Device {
        val device = deviceRepository.findByIdAndUser(id, currentUserDTO.user)
            ?: throw NotFoundException("Dispositivo não encontrado")

        if (requestDTO.category.id == null)
            throw NotFoundException("Categoria não encontrada")

        val category = categoryRepository.findById(requestDTO.category.id)
        if (category.isEmpty)
            throw NotFoundException("Categoria não encontrada")

        device.category = category.get()

        device.name = requestDTO.name

        return deviceRepository.save(device)
    }

    fun delete(id: Long) = deviceRepository.deleteByIdAndUser(id, currentUserDTO.user)
}
