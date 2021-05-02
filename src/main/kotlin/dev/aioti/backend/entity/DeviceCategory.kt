package dev.aioti.backend.entity

import dev.aioti.backend.dto.request.DeviceTypeRegisterDTO
import javax.persistence.*

@Entity
@Table(name = "TB_DEVICE_TYPE")
class DeviceCategory(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID_DEVICE_TYPE")
    val id: Long?,

    @Column(name = "NAME_DEVICE_TYPE")
    var name: String?,

    @Column(name = "DESCRIPTION_DEVICE_TYPE")
    var description: String?
) {
    constructor(deviceTypeRegisterDTO: DeviceTypeRegisterDTO) : this(
        null,
        deviceTypeRegisterDTO.name,
        deviceTypeRegisterDTO.description
    )

    constructor(id: Long?) : this(id, null, null)
}
