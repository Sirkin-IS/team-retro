package ru.microsrv.teamretroservice.model.web.response.retro

import java.util.*


/**
 * Базовый ответ на запрос.
 *
 * @param id идентификатор созданного/обновлённого/удалённого объекта
 * если объект не найден / не создан / не обновлён, то вернется id = null
 */
data class BaseResponse(
    val id: UUID?
)
