package dev.aioti.backend.respository

import dev.aioti.backend.entity.Device
import dev.aioti.backend.entity.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import javax.transaction.Transactional

interface DeviceRepository : JpaRepository<Device?, Long?> {
    fun findAllByUser(user: User): List<Device?>
    fun findByIdAndUser(id: Long, user: User): Device?

    @Transactional
    @Modifying
    fun deleteByIdAndUser(id: Long, user: User)
}
