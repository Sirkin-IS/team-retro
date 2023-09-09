package ru.microsrv.teamretroservice.service

import java.time.ZonedDateTime
import java.util.*
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Service
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PutMapping
import ru.microsrv.teamretroservice.mapper.RetroMapper
import ru.microsrv.teamretroservice.model.common.PageableResponse
import ru.microsrv.teamretroservice.model.entity.RetroEntity
import ru.microsrv.teamretroservice.model.web.request.retro.CreateRetroRequest
import ru.microsrv.teamretroservice.model.web.request.retro.GetRetroListRequest
import ru.microsrv.teamretroservice.model.web.request.retro.UpdateRetroRequest
import ru.microsrv.teamretroservice.model.web.response.retro.BaseResponse
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

    fun createRetro(request: CreateRetroRequest): BaseResponse {
        val newRetro = retroMapper.toRetroEntity(request)
        val retroId = retroRepository.save(newRetro).retroId
        return BaseResponse(retroId)
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

    /**
     * Обновление параметров ретро.
     */
    @PutMapping("{retroId}")
    fun updateRetro(@PathVariable retroId: UUID, request: UpdateRetroRequest): BaseResponse {
        val retroEntity: RetroEntity = retroRepository.findById(retroId).orElse(null)
            ?: return BaseResponse(null)

        if (retroEntity.caption != request.caption || retroEntity.description != request.description) {
            retroEntity.caption = request.caption
            retroEntity.description = request.description
            retroEntity.updateDttm = ZonedDateTime.now()
            retroRepository.save(retroEntity)
            return BaseResponse(retroEntity.retroId)
        }

        return BaseResponse(null)
    }

}
