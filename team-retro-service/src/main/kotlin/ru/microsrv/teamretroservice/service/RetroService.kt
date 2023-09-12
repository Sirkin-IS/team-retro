package ru.microsrv.teamretroservice.service

import java.time.ZonedDateTime
import java.util.*
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import ru.microsrv.teamretroservice.mapper.NoteMapper
import ru.microsrv.teamretroservice.mapper.RetroMapper
import ru.microsrv.teamretroservice.model.common.PageableResponse
import ru.microsrv.teamretroservice.model.entity.RetroEntity
import ru.microsrv.teamretroservice.model.web.request.note.CreateNoteRequest
import ru.microsrv.teamretroservice.model.web.request.note.UpdateNoteRequest
import ru.microsrv.teamretroservice.model.web.request.retro.CreateRetroRequest
import ru.microsrv.teamretroservice.model.web.request.retro.GetRetroListRequest
import ru.microsrv.teamretroservice.model.web.request.retro.UpdateRetroRequest
import ru.microsrv.teamretroservice.model.web.response.retro.BaseResponse
import ru.microsrv.teamretroservice.model.web.response.retro.GetRetroListResponse
import ru.microsrv.teamretroservice.model.web.response.retro.GetRetroResponse
import ru.microsrv.teamretroservice.repository.NoteRepository
import ru.microsrv.teamretroservice.repository.RetroRepository

/**
 * Сервис для работы с объектами ретро.
 */
@Service
class RetroService(
    val retroRepository: RetroRepository,
    val noteRepository: NoteRepository,
    val retroMapper: RetroMapper,
    val noteMapper: NoteMapper
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

    fun updateRetro(retroId: UUID, request: UpdateRetroRequest): BaseResponse {
        val retroEntity: RetroEntity = retroRepository.findById(retroId).orElse(null)
            ?: return BaseResponse(null)

        if (retroEntity.caption != request.caption || retroEntity.description != request.description) {
            retroEntity.caption = request.caption
            retroEntity.description = request.description
            retroEntity.updateDttm = ZonedDateTime.now()
            val result = retroRepository.save(retroEntity)
            return BaseResponse(result.retroId)
        }

        return BaseResponse(null)
    }

    @Transactional
    fun deleteRetro(retroId: UUID): BaseResponse {
        retroRepository.deleteById(retroId)
        noteRepository.deleteByRetroId(retroId)
        return BaseResponse(retroId)
    }

    fun createNote(retroId: UUID, request: CreateNoteRequest): BaseResponse {
        retroRepository.findById(retroId).orElse(null)
            ?: return BaseResponse(null)

        val note = noteMapper.toNoteEntity(retroId, request)
        val res = noteRepository.save(note)
        return BaseResponse(res.noteId)
    }

    fun updateNote(request: UpdateNoteRequest): BaseResponse {
        val noteEntity = noteRepository.findById(request.noteId).orElse(null) ?: return BaseResponse(null)

        if (noteEntity.caption != request.caption || (request.text != null && noteEntity.text != request.text)) {
            noteEntity.caption = request.caption
            if (request.text != null) {
                noteEntity.text = request.text
            }

            val result = noteRepository.save(noteEntity)
            return BaseResponse(result.noteId)
        }

        return BaseResponse(null)
    }

}
