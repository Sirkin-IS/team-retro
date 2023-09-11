package ru.microsrv.teamretroservice.model.web.request.note

import jakarta.validation.constraints.Size
import java.util.*
import ru.microsrv.teamretroservice.model.enums.StageType

data class CreateNoteRequest(

    var userId: UUID,

    var stageType: StageType,

    @field:Size(max = 50)
    var caption: String? = null,

    @field:Size(max = 4096)
    var text: String
)
