package dev.aioti.backend.entity

import javax.persistence.*

@Entity
class Device(
    @Id @GeneratedValue val id: Long,
    val name: String,
    val mac: String,
    @ManyToOne val user: User,
    @ManyToOne val area: Area?
)
