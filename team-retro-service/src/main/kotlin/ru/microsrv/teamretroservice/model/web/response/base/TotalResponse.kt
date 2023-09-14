package ru.microsrv.teamretroservice.model.web.response.base


/**
 * Ответ на запрос.
 *
 * @param count количество созданных/обновлённых/удалённых объектов
 */
data class TotalResponse(
    val count: Long? = null
)
