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
import ru.microsrv.teamretroservice.model.enums.StageType


/**
 * Сущность заметка.
 */
@Entity
@Table(schema = "public", name = "note")
class NoteEntity {

    /**
     * Идентификатор заметки.
     */
    @Id
    @Column(name = "note_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.UUID)
    lateinit var noteId: UUID

    /**
     * Идентификатор ретро.
     */
    @Column(name = "retro_id", nullable = false)
    lateinit var retroId: UUID

    /**
     * Идентификатор пользователя.
     */
    @Column(name = "user_id", nullable = false)
    var userId: UUID? = null

    /**
     * Этап ретро.
     */
    @Enumerated(EnumType.STRING)
    @Column(name = "stage_type", nullable = false)
    var stageType: StageType? = null

    /**
     * Заголовок.
     */
    @Column(name = "caption")
    @Size(max = 50)
    var caption: String? = null

    /**
     * Текст заметки.
     */
    @Column(name = "text")
    @Size(max = 4096)
    var text: String? = null
}
