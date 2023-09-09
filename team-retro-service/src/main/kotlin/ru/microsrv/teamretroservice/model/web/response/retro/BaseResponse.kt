package ru.microsrv.teamretroservice.model.web.response.retro

import java.util.*


/**
 * Базовый ответ на запрос.
 *
 * @param id идентификатор созданного/обновлённого объекта
 */
data class BaseResponse(
    val id: UUID?
)
