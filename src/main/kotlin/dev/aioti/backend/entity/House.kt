package dev.aioti.backend.entity

import dev.aioti.backend.dto.request.CreateHouseRequestDTO
import javax.persistence.*

@Entity
@Table(name = "TB_HOUSE")
class House(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID_HOUSE")
    val id: Long?,

    @Column(name = "NAME_HOUSE")
    val name: String,

    @Column(name = "LOCATION_HOUSE")
    val location: String?,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_USER")
    val user: User,

    @ManyToMany(fetch = FetchType.LAZY, cascade = [CascadeType.ALL])
    val usersPermitted: MutableSet<User>,

    @OneToMany(fetch = FetchType.LAZY, cascade = [CascadeType.ALL])
    @JoinColumn(name = "ID_DEVICE")
    val devices: MutableSet<Device>
) {
    constructor(createHouseRequestDTO: CreateHouseRequestDTO, user: User) : this(
        null,
        createHouseRequestDTO.name,
        createHouseRequestDTO.location,
        user,
        mutableSetOf(),
        mutableSetOf()
    )
}
