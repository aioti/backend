package dev.aioti.backend.entity

import dev.aioti.backend.dto.request.UserRegisterRequestDTO
import javax.persistence.*

@Entity
@Table(
    name = "TB_USER",
    uniqueConstraints = [UniqueConstraint(columnNames = arrayOf("EMAIL_USER"))]
)
class User(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID_USER")
    val id: Long?,

    @Column(name = "NAME_USER")
    val name: String,

    @Column(name = "EMAIL_USER")
    val email: String,

    @Column(name = "ISSUER_USER")
    val issuer: String?,

    @Column(name = "EMAIL_VERIFIED_USER")
    val emailVerified: Boolean?
) {
    constructor(userRegisterRequestDTO: UserRegisterRequestDTO) : this(
        null,
        userRegisterRequestDTO.name,
        userRegisterRequestDTO.email,
        null,
        null
    )
}
