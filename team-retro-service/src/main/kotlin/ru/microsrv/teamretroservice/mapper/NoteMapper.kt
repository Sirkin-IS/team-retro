package ru.microsrv.teamretroservice.mapper

import java.util.*
import org.mapstruct.Mapper
import ru.microsrv.teamretroservice.model.dto.NoteDto
import ru.microsrv.teamretroservice.model.entity.NoteEntity
import ru.microsrv.teamretroservice.model.web.request.note.CreateNoteRequest
import kotlin.collections.List


/**
 * Маппер заметок.
 */
@Mapper
abstract class NoteMapper {

    abstract fun toNoteEntity(retroId: UUID, request: CreateNoteRequest) : NoteEntity

    abstract fun toNoteDto(noteEntity: NoteEntity): NoteDto

    abstract fun toNoteDto(noteEntity: Iterable<NoteEntity>): List<NoteDto>
}
