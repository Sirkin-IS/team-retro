package ru.microsrv.teamretroservice.service

import java.util.*
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Service
import ru.microsrv.teamretroservice.mapper.RetroMapper
import ru.microsrv.teamretroservice.model.common.PageableResponse
import ru.microsrv.teamretroservice.model.entity.RetroEntity
import ru.microsrv.teamretroservice.model.web.request.retro.CreateRetroRequest
import ru.microsrv.teamretroservice.model.web.request.retro.GetRetroListRequest
import ru.microsrv.teamretroservice.model.web.response.retro.BaseRetroResponse
import ru.microsrv.teamretroservice.model.web.response.retro.GetRetroListResponse
import ru.microsrv.teamretroservice.model.web.response.retro.GetRetroResponse
import ru.microsrv.teamretroservice.repository.RetroRepository

/**
 * Сервис для работы с объектами ретро.
 */
@Service
class RetroService(
    val retroRepository: RetroRepository,
    val retroMapper: RetroMapper
) {

    fun createRetro(request: CreateRetroRequest): BaseRetroResponse {
        val newRetro = retroMapper.toRetroEntity(request)
        val retroId = retroRepository.save(newRetro).retroId
        return BaseRetroResponse(retroId)
    }

    fun getRetroList(request: GetRetroListRequest): GetRetroListResponse {
        val pageRequest = PageRequest.of(request.pageNumber, request.pageSize)
        val page = retroRepository.findAll(pageRequest)
        val retroList = retroMapper.toRetroDto(page)
        val paging = PageableResponse.paging(request, page)
        return GetRetroListResponse(retroList, paging)
    }

    fun getRetro(retroId: UUID): GetRetroResponse {
        val retroEntity: RetroEntity? = retroRepository.findById(retroId).orElse(null)
        val retro = retroMapper.toRetroDto(retroEntity)
        return GetRetroResponse(retro)
    }

}
