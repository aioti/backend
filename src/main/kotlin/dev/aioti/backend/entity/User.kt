package dev.aioti.backend.entity

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
class User(
    @Id @GeneratedValue val id: Long?,
    val name: String,
    val email: String,
    private val password: String
)
