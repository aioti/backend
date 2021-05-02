package dev.aioti.backend.entity

import dev.aioti.backend.dto.request.HouseRequestDTO
import javax.persistence.*

@Entity
@Table(name = "TB_HOUSE")
class House(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID_HOUSE")
    val id: Long?,

    @Column(name = "NAME_HOUSE")
    var name: String,

    @Column(name = "LOCATION_HOUSE")
    var location: String?,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_USER")
    val user: User,

    @ManyToMany(fetch = FetchType.LAZY, cascade = [CascadeType.ALL])
    val usersPermitted: MutableSet<User>,

    @OneToMany(fetch = FetchType.LAZY, cascade = [CascadeType.ALL])
    val devices: MutableSet<Device>
) {
    constructor(houseRequestDTO: HouseRequestDTO, user: User) : this(
        null,
        houseRequestDTO.name,
        houseRequestDTO.location,
        user,
        mutableSetOf(),
        mutableSetOf()
    )

    constructor(requestDTO: HouseRequestDTO) : this(
        null,
        requestDTO.name,
        requestDTO.location,
        User(null, "", "", null, null),
        mutableSetOf(),
        mutableSetOf()
    )
}
