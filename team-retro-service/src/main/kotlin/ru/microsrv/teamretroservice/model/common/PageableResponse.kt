package ru.microsrv.teamretroservice.model.common

import org.springframework.data.domain.Page


/**
 * Ответ с пагинацией по страницам.
 */
class PageableResponse : PageableRequest() {
    var totalPages: Int = 0
    var totalElements: Long = 0

    companion object {
        fun <T> paging(request: PageableRequest, response: Page<T>): PageableResponse {
            return PageableResponse().apply {
                pageNumber = request.pageNumber
                pageSize = request.pageSize
                totalPages = response.totalPages
                totalElements = response.totalElements
            }
        }
    }
}
