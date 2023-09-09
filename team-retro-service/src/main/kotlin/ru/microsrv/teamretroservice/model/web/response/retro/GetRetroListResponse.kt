package ru.microsrv.teamretroservice.model.web.response.retro

import ru.microsrv.teamretroservice.model.common.PageableResponse
import ru.microsrv.teamretroservice.model.dto.RetroDto


/**
 * Ответ на запрос списка ретро.
 *
 * @param retroList список объектов ретро
 * @param paging данные о пагинации
 */
data class GetRetroListResponse(
    val retroList: List<RetroDto>,
    var paging: PageableResponse
)
