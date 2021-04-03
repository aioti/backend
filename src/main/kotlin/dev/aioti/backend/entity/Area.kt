package dev.aioti.backend.entity

import javax.persistence.*

@Entity
class Area(
    @Id @GeneratedValue val id: Long,
    val name: String,
    val location: String,
    @OneToOne val user: User,
    @OneToMany val usersPermitted: List<User>
)
