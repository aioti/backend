package dev.aioti.backend.entity

import com.google.firebase.auth.FirebaseToken
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
    var name: String,

    @Column(name = "EMAIL_USER")
    var email: String,

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

    constructor(firebaseUser: FirebaseToken) : this(
        null,
        firebaseUser.name ?: "",
        firebaseUser.email,
        firebaseUser.issuer,
        firebaseUser.isEmailVerified
    )

    override fun equals(other: Any?) = (other is User && other.id == id)
    override fun hashCode(): Int {
        var result = id?.hashCode() ?: 0
        result = 31 * result + email.hashCode()
        return result
    }
}
