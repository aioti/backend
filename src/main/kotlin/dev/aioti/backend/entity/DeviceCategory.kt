package dev.aioti.backend.entity

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

@Entity
class DeviceCategory(
    @Id @GeneratedValue val id: Long,
    val name: String?,
    val description: String?
)
