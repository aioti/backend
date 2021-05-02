package dev.aioti.backend.respository

import dev.aioti.backend.entity.DeviceCategory
import org.springframework.data.jpa.repository.JpaRepository

interface DeviceTypeRepository : JpaRepository<DeviceCategory?, Long?>
