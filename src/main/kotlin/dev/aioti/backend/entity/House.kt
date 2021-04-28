package dev.aioti.backend.entity

import javax.persistence.*

@Entity
@Table(name = "TB_HOUSE")
class House(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID_HOUSE")
    val id: Long,

    @Column(name = "NAME_HOUSE")
    val name: String,

    @Column(name = "LOCATION_HOUSE")
    val location: String,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_USER")
    val user: User,

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_USER")
    val usersPermitted: List<User>,

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_DEVICE")
    val devices: List<Device>
)
