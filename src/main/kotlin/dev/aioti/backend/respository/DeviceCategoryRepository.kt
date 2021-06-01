package dev.aioti.backend.respository

import dev.aioti.backend.entity.DeviceCategory
import org.springframework.data.jpa.repository.JpaRepository

interface DeviceCategoryRepository : JpaRepository<DeviceCategory?, Long?> {
    fun findByName(name: String): DeviceCategory?
}
