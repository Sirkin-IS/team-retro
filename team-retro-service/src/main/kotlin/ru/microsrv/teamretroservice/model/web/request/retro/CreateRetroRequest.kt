package ru.microsrv.teamretroservice.model.web.request.retro


/**
 * Запрос для создания нового ретро.
 */
data class CreateRetroRequest(
    val caption: String?,
    val description: String?
)
