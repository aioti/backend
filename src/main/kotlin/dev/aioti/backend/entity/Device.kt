package dev.aioti.backend.entity

import javax.persistence.*

@Entity
@Table(name = "TB_DEVICE")
class Device(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID_DEVICE")
    val id: Long,

    @Column(name = "NAME_DEVICE")
    val name: String,

    @Column(name = "UUID_DEVICE")
    val uuid: String,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_USER")
    val user: User,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_HOUSE")
    val area: House?
)
