package ru.microsrv.teamretroservice.model.web.response.retro

import ru.microsrv.teamretroservice.model.dto.RetroDto


/**
 * Ответ на запрос ретро.
 *
 * @param retro объект ретро
 */
data class GetRetroResponse(
    val retro: RetroDto? = null
)
