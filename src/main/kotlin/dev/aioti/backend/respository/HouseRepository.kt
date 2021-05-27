package dev.aioti.backend.respository

import dev.aioti.backend.dto.response.HouseResponseDTO
import dev.aioti.backend.entity.Device
import dev.aioti.backend.entity.House
import dev.aioti.backend.entity.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import javax.transaction.Transactional

interface HouseRepository : JpaRepository<House?, Long?> {

    @Query("SELECT h.id AS id, h.name AS name, h.location AS location, h.user AS user " +
            "FROM House h " +
            "LEFT JOIN h.usersPermitted u " +
            "WHERE u = ?1 or h.user = ?1")
    fun findByUser(user: User): List<HouseResponseDTO>

    @Query("SELECT h FROM House h " +
            "LEFT JOIN h.usersPermitted u " +
            "WHERE (u = ?2 or h.user = ?2) and h.id = ?1")
    fun findIfPermitted(id: Long, user: User): House?

    @Transactional
    @Modifying
    fun removeByIdAndUser(id: Long, user: User)

    fun findByIdAndUser(id: Long, user: User): House?

    @Query("SELECT h FROM House h JOIN FETCH h.devices d WHERE d.id = ?1")
    fun findByDeviceId(deviceId: Long): List<House>
}
