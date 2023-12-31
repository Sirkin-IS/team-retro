package ru.microsrv.teamretroservice.model.dto

import com.fasterxml.jackson.annotation.JsonInclude
import java.time.ZonedDateTime
import java.util.*


/**
 * DTO ретро.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
data class RetroDto(

    val retroId: UUID,
    val caption: String? = null,
    val description: String? = null,
    val createDttm: ZonedDateTime? = null,
    val updateDttm: ZonedDateTime? = null
)
