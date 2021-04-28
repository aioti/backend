package dev.aioti.backend.respository

import dev.aioti.backend.entity.User
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository: JpaRepository<User?, Long?>
