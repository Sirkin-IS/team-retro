package ru.microsrv.teamretroservice.model.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table
import jakarta.validation.constraints.Size
import java.time.ZonedDateTime
import java.util.*


/**
 * Сущность ретро.
 */
@Entity
@Table(schema = "public", name = "retro")
class RetroEntity {

    /**
     * Идентификатор ретро.
     */
    @Id
    @Column(name = "retro_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.UUID)
    lateinit var retroId: UUID

    /**
     * Заголовок.
     */
    @Column(name = "caption")
    @Size(max = 50)
    var caption: String? = null

    /**
     * Описание.
     */
    @Column(name = "description")
    @Size(max = 100)
    var description: String? = null

    /**
     * Время создания.
     */
    @Column(name = "create_dttm")
    var createDttm: ZonedDateTime? = null

    /**
     * Время обновления.
     */
    @Column(name = "update_dttm")
    var updateDttm: ZonedDateTime? = null
}
