package dev.aioti.backend.respository

import dev.aioti.backend.entity.DeviceType
import org.springframework.data.jpa.repository.JpaRepository

interface DeviceTypeRepository : JpaRepository<DeviceType?, Long?>
