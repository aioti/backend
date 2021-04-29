package dev.aioti.backend.entity

import javax.persistence.*

@Entity
@Table(name = "TB_DEVICE_CATEGORY")
class DeviceCategory(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID_DEVICE_CATEGORY")
    val id: Long,

    @Column(name = "NAME_DEVICE_CATEGORY")
    val name: String?,

    @Column(name = "DESCRIPTION_DEVICE_CATEGORY")
    val description: String?
)
