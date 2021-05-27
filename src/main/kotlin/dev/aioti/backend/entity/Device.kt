package dev.aioti.backend.entity

import dev.aioti.backend.dto.request.DeviceRegisterRequestDTO
import org.hibernate.annotations.OnDelete
import org.hibernate.annotations.OnDeleteAction
import javax.persistence.*

@Entity
@Table(name = "TB_DEVICE")
class Device(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID_DEVICE")
    val id: Long?,

    @Column(name = "NAME_DEVICE")
    var name: String,

    @ManyToOne(fetch = FetchType.LAZY)
    var category: DeviceCategory,

    @ManyToOne(fetch = FetchType.LAZY)
    val user: User,

    @Column(name = "UUID_DEVICE")
    val uuid: String?,

    @ManyToOne(fetch = FetchType.LAZY, cascade = [CascadeType.ALL])
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "ID_HOUSE")
    var house: House?
) {
    constructor(requestDTO: DeviceRegisterRequestDTO, user: User) : this(
        null,
        requestDTO.name,
        DeviceCategory(requestDTO.category.id),
        user,
        null,
        null
    )
}
