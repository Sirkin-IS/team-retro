package ru.microsrv.teamretroservice.model.web.request.note

import java.util.*

data class UpdateNoteRequest(

    val noteId: UUID,
    val caption: String? = null,
    val text: String? = null
)
