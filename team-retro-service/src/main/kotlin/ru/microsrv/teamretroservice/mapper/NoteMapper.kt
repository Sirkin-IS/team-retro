package ru.microsrv.teamretroservice.mapper

import java.util.*
import org.mapstruct.Mapper
import ru.microsrv.teamretroservice.model.entity.NoteEntity
import ru.microsrv.teamretroservice.model.web.request.note.CreateNoteRequest


/**
 * Маппер заметок.
 */
@Mapper
abstract class NoteMapper {

    abstract fun toNoteEntity(retroId: UUID, request: CreateNoteRequest) : NoteEntity
}
