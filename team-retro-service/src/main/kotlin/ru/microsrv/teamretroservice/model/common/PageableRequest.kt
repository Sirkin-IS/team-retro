package ru.microsrv.teamretroservice.model.common


/**
 * Запрос с пагинацией по страницам.
 */
open class PageableRequest {
    var pageNumber: Int = 0
    var pageSize: Int = DEFAULT_PAGE_SIZE

    companion object {
        const val DEFAULT_PAGE_SIZE = 10
    }
}
