package ru.microsrv.teamretroservice.model.dto

import java.time.ZonedDateTime
import java.util.*


/**
 * DTO ретро.
 */
data class RetroDto(

    var retroId: UUID?,
    var caption: String?,
    var description: String?,
    var createDttm: ZonedDateTime?,
    var updateDttm: ZonedDateTime?
)
