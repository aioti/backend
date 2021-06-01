package dev.aioti.backend.service

import dev.aioti.backend.dto.request.HouseRequestDTO
import dev.aioti.backend.entity.House
import dev.aioti.backend.entity.User
import dev.aioti.backend.exception.NotFoundException
import dev.aioti.backend.respository.DeviceRepository
import dev.aioti.backend.respository.HouseRepository
import dev.aioti.backend.respository.UserRepository
import org.springframework.stereotype.Service

@Service
class HouseService(
    private val houseRepository: HouseRepository,
    private val userRepository: UserRepository,
    private val deviceRepository: DeviceRepository
) {

    fun houses(user: User) = houseRepository.findByUser(user)
    fun create(house: HouseRequestDTO, user: User) = houseRepository.save(House(house, user))
    fun house(id: Long, user: User) =
        houseRepository.findIfPermitted(id, user) ?: throw NotFoundException("Casa não encontrada")

    fun delete(id: Long, user: User) = houseRepository.removeByIdAndUser(id, user)

    fun permitUser(houseId: Long, userEmail: String, user: User) {
        val (house, permittedUser) = findHouseByIdAndUser(houseId, userEmail, user)

        house.usersPermitted.add(permittedUser)
        houseRepository.save(house)
    }

    fun revokeUser(houseId: Long, userId: Long, user: User) {
        val (house, permittedUser) = findHouseByIdAndUser(houseId, userId, user)

        house.usersPermitted.remove(permittedUser)
        houseRepository.save(house)
    }

    private fun findHouseByIdAndUser(
        houseId: Long,
        userEmail: String,
        user: User
    ): Pair<House, User> {
        val house = houseRepository.findByIdAndUser(houseId, user)
        val permittedUser = userRepository.findByEmail(userEmail)

        if (house == null)
            throw NotFoundException("Casa não encontrada")
        if (permittedUser == null)
            throw NotFoundException("Usuário não encontrado")

        return Pair(house, permittedUser)
    }

    private fun findHouseByIdAndUser(
        houseId: Long,
        userId: Long,
        user: User
    ): Pair<House, User> {
        val house = houseRepository.findByIdAndUser(houseId, user)
        val permittedUser = userRepository.findById(userId)

        if (house == null)
            throw NotFoundException("Casa não encontrada")
        if (permittedUser.isEmpty)
            throw NotFoundException("Usuário não encontrado")

        return Pair(house, permittedUser.get())
    }

    fun update(id: Long, houseParams: HouseRequestDTO, user: User): House {
        val house = houseRepository.findByIdAndUser(id, user)
            ?: throw NotFoundException("Casa não encontrada")

        house.name = houseParams.name
        house.location = houseParams.location

        return houseRepository.save(house)
    }

    fun addDevice(houseId: Long, deviceId: Long, user: User) {
        val house = houseRepository.findByIdAndUser(houseId, user)
            ?: throw NotFoundException("Casa não encontrada")

        val device = deviceRepository.findByIdAndUser(deviceId, user)
            ?: throw NotFoundException("Dispositivo não encontrado")

        house.devices.add(device)

        houseRepository.save(house)
    }

    fun removeDevice(houseId: Long, deviceId: Long, user: User) {
        val house = houseRepository.findByIdAndUser(houseId, user)
            ?: throw NotFoundException("Casa não encontrada")

        val device = deviceRepository.findByIdAndUser(deviceId, user)
            ?: throw NotFoundException("Dispositivo não encontrado")

        house.devices.remove(device)

        houseRepository.save(house)
    }
}
