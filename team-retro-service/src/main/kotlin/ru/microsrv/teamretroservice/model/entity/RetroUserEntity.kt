package ru.microsrv.teamretroservice.model.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table
import jakarta.validation.constraints.Size
import java.util.*
import ru.microsrv.teamretroservice.model.enums.UserRole


/**
 * Сущность пользователь.
 */
@Entity
@Table(schema = "public", name = "retro_user")
class RetroUserEntity {

    /**
     * Идентификатор пользователя.
     */
    @Id
    @Column(name = "user_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    lateinit var userId: UUID

    /**
     * Логин.
     */
    @Column(name = "login", nullable = false)
    @Size(max = 100)
    lateinit var login: String

    /**
     * Хэш пароля.
     */
    @Column(name = "password_hash", nullable = false)
    @Size(max = 100)
    lateinit var passwordHash: String

    /**
     * Перец.
     */
    @Column(name = "pepper")
    @Size(max = 100)
    lateinit var pepper: String

    /**
     * ФИО.
     */
    @Column(name = "name")
    @Size(max = 100)
    var name: String? = null

    /**
     * Роль пользователя.
     */
    @Size(max = 20)
    @Enumerated(EnumType.STRING)
    @Column(name = "role", nullable = false)
    lateinit var role: UserRole
}
