package ru.microsrv.teamretroservice.model.web.request.note

import java.util.*

data class DeleteNoteRequest(
    val ids: List<UUID>
)
