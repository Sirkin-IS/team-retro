package ru.microsrv.teamretroservice.model.dto

import com.fasterxml.jackson.annotation.JsonInclude
import java.util.*
import ru.microsrv.teamretroservice.model.enums.StageType

/**
 * DTO заметки.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
data class NoteDto(

    val noteId: UUID,
    val retroId: UUID,
    val stageType: StageType,
    val caption: String? = null,
    val text: String
)
