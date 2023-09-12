package ru.microsrv.teamretroservice.repository

import java.util.*
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import ru.microsrv.teamretroservice.model.entity.NoteEntity

/**
 * Репозиторий для работы с заметками.
 */
@Repository
interface NoteRepository : JpaRepository<NoteEntity, UUID> {

    fun deleteByRetroId(id: UUID): Long

    fun deleteByNoteIdIn(noteId: Collection<UUID>): Long
}
