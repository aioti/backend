package dev.aioti.backend.entity

import dev.aioti.backend.dto.request.DeviceCategoryRegisterDTO
import javax.persistence.*

@Entity
@Table(name = "TB_DEVICE_CATEGORY")
class DeviceCategory(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID_DEVICE_CATEGORY")
    val id: Long?,

    @Column(name = "NAME_DEVICE_CATEGORY")
    var name: String?,

    @Column(name = "DESCRIPTION_DEVICE_CATEGORY")
    var description: String?
) {
    constructor(deviceCategoryRegisterDTO: DeviceCategoryRegisterDTO) : this(
        null,
        deviceCategoryRegisterDTO.name,
        deviceCategoryRegisterDTO.description
    )

    constructor(id: Long?) : this(id, null, null)
}
