package ru.microsrv.teamretroservice.model.web.request.note

import jakarta.validation.constraints.Size
import java.util.*
import ru.microsrv.teamretroservice.model.enums.StageType

data class CreateNoteRequest(

    val userId: UUID,

    val stageType: StageType,

    @field:Size(max = 50)
    val caption: String? = null,

    @field:Size(max = 4096)
    val text: String
)
