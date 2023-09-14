package ru.microsrv.teamretroservice.model.web.response.note

import ru.microsrv.teamretroservice.model.dto.NoteDto


/**
 * Ответ на запрос списка заметок.
 *
 * @param noteList список заметок
 */
data class GetNoteListResponse(
    val noteList: List<NoteDto>
)
