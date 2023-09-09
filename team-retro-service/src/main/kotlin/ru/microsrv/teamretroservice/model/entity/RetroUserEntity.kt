package ru.microsrv.teamretroservice.model.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table
import jakarta.validation.constraints.Size
import java.util.*


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
    var userId: UUID? = null

    /**
     * Логин.
     */
    @Column(name = "login", nullable = false)
    @Size(max = 100)
    var login: String? = null

    /**
     * Хэш пароля.
     */
    @Column(name = "password_hash", nullable = false)
    @Size(max = 100)
    var passwordHash: String? = null

    /**
     * Перец.
     */
    @Column(name = "pepper")
    @Size(max = 100)
    var pepper: String? = null

    /**
     * ФИО.
     */
    @Column(name = "name")
    @Size(max = 100)
    var name: String? = null
}
