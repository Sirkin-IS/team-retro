package ru.microsrv.teamretroservice.model.web.response.retro

import java.util.*


/**
 * Базовый ответ на запрос.
 *
 * @param id идентификатор созданного объекта
 */
data class BaseRetroResponse(
    val id: UUID
)
