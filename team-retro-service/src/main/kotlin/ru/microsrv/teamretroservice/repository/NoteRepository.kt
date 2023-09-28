package ru.microsrv.teamretroservice.repository

import java.util.*
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import ru.microsrv.teamretroservice.model.entity.NoteEntity
import ru.microsrv.teamretroservice.model.enums.StageType


/**
 * Репозиторий для работы с заметками.
 */
@Repository
interface NoteRepository : JpaRepository<NoteEntity, UUID> {

    fun deleteByRetroId(id: UUID): Long

    fun deleteByNoteIdIn(noteIds: Collection<UUID>): Long

    fun findByNoteIdIn(noteIds: Collection<UUID>): List<NoteEntity>

    fun findByRetroId(retroId: UUID): List<NoteEntity>

    fun findByRetroIdAndStageTypeIn(retroId: UUID, stageTypes: Collection<StageType>): List<NoteEntity>
}
