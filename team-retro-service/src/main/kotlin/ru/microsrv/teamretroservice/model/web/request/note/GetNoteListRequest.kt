package ru.microsrv.teamretroservice.model.web.request.note

import ru.microsrv.teamretroservice.model.enums.StageType

data class GetNoteListRequest(
    val stageType: List<StageType>? = listOf()
)
