package dev.aioti.backend.service

import dev.aioti.backend.dto.request.DeviceRegisterRequestDTO
import dev.aioti.backend.entity.Device
import dev.aioti.backend.entity.User
import dev.aioti.backend.exception.NotFoundException
import dev.aioti.backend.respository.DeviceRepository
import dev.aioti.backend.respository.DeviceCategoryRepository
import dev.aioti.backend.respository.HouseRepository
import org.springframework.stereotype.Service

@Service
class DeviceService(
    private val deviceRepository: DeviceRepository,
    private val categoryRepository: DeviceCategoryRepository,
    private val houseRepository: HouseRepository
) {
    fun create(requestDTO: DeviceRegisterRequestDTO, user: User): Device {

        val category = requestDTO.category.id?.let { categoryRepository.findById(it) }?.get()
            ?: throw NotFoundException("Categoria não encontrada")

        val device = Device(
            null,
            requestDTO.name,
            category,
            user,
            requestDTO.uuid,
            null
        )

        return deviceRepository.save(device)
    }


    fun device(id: Long) = deviceRepository.findById(id)
    fun devices(user: User) = deviceRepository.findAllByUser(user)

    fun update(id: Long, requestDTO: DeviceRegisterRequestDTO, user: User): Device {
        val device = deviceRepository.findByIdAndUser(id, user)
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

    fun delete(id: Long, user: User) {
        val device = deviceRepository.findByIdAndUser(id, user)!!

        val houses = houseRepository.findAllByDevicesContaining(mutableSetOf(device))

        houses.forEach { house ->
            house.devices.removeAll { device -> device.id == id }
        }

        houseRepository.saveAll(houses)
        deviceRepository.deleteByIdAndUser(id, user)
    }
}
