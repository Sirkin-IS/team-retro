package ru.microsrv.teamretroservice.service

import io.undertow.util.BadRequestException
import java.time.ZonedDateTime
import java.util.*
import org.springframework.data.domain.PageRequest
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.server.ResponseStatusException
import ru.microsrv.teamretroservice.mapper.NoteMapper
import ru.microsrv.teamretroservice.mapper.RetroMapper
import ru.microsrv.teamretroservice.model.common.PageableResponse
import ru.microsrv.teamretroservice.model.entity.NoteEntity
import ru.microsrv.teamretroservice.model.entity.RetroEntity
import ru.microsrv.teamretroservice.model.web.request.note.CreateNoteRequest
import ru.microsrv.teamretroservice.model.web.request.note.DeleteNoteRequest
import ru.microsrv.teamretroservice.model.web.request.note.GetNoteListRequest
import ru.microsrv.teamretroservice.model.web.request.note.MergeNoteRequest
import ru.microsrv.teamretroservice.model.web.request.note.UpdateNoteRequest
import ru.microsrv.teamretroservice.model.web.request.retro.CreateRetroRequest
import ru.microsrv.teamretroservice.model.web.request.retro.GetRetroListRequest
import ru.microsrv.teamretroservice.model.web.request.retro.UpdateRetroRequest
import ru.microsrv.teamretroservice.model.web.response.base.BaseResponse
import ru.microsrv.teamretroservice.model.web.response.base.TotalResponse
import ru.microsrv.teamretroservice.model.web.response.note.GetNoteListResponse
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
    fun deleteRetro(retroId: UUID): TotalResponse {
        val result = retroRepository.deleteByRetroId(retroId)
        noteRepository.deleteByRetroId(retroId)
        return TotalResponse(result)
    }

    fun createNote(retroId: UUID, request: CreateNoteRequest): BaseResponse {
        retroRepository.findById(retroId).orElse(null)
            ?: return BaseResponse(null)

        val note = noteMapper.toNoteEntity(retroId, request)
        val res = noteRepository.save(note)
        return BaseResponse(res.noteId)
    }

    fun getNoteList(retroId: UUID, request: GetNoteListRequest): GetNoteListResponse {
        val result = if (request.stageType?.isEmpty() != false) {
            noteRepository.findByRetroId(retroId)
        } else {
            noteRepository.findByRetroIdAndStageTypeIn(retroId, request.stageType)
        }

        val noteDtoList = noteMapper.toNoteDto(result)
        return GetNoteListResponse(noteDtoList)
    }

    fun updateNote(request: UpdateNoteRequest): BaseResponse {
        val noteEntity = noteRepository.findById(request.noteId).orElse(null) ?: return BaseResponse(null)

//        if (noteEntity.userId != currentUser.userId) {// todo: редактировать заметку может только хозяин заметки
//            return BaseResponse(null)
//        }

        if (noteEntity.caption != request.caption || (request.text != null && noteEntity.text != request.text)) {
            noteEntity.caption = request.caption
            noteEntity.text = request.text ?: noteEntity.text

            val result = noteRepository.save(noteEntity)
            return BaseResponse(result.noteId)
        }

        return BaseResponse(null)
    }

    @Transactional
    fun deleteNotes(request: DeleteNoteRequest): TotalResponse {
        val result = noteRepository.deleteByNoteIdIn(request.ids)
        return TotalResponse(result)
    }

    @Transactional
    fun mergeNote(request: MergeNoteRequest): BaseResponse {
        val noteList = noteRepository.findByNoteIdIn(request.ids)
        if (noteList.size < 2) {
            return BaseResponse(null)
        }

        val donor = noteList.first()

        noteList.forEach {
            if (donor.retroId != it.retroId || donor.stageType != it.stageType) {
                throw ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Validation request error", BadRequestException("Not valid stageType or retroId")
                )
            }
        }

        val mergedText = noteList.joinToString(MERGED_TEXT_SEPARATOR) { it.text }
        val mergedCaption = noteList
            .filter { it.caption != null }
            .joinToString(MERGED_CAPTION_SEPARATOR) { it.caption!! }

        val newNote = NoteEntity()
        newNote.retroId = donor.retroId
        newNote.userId = donor.userId
        newNote.stageType = donor.stageType
        newNote.caption = mergedCaption
        newNote.text = mergedText

        noteRepository.deleteByNoteIdIn(request.ids)
        val result = noteRepository.save(newNote)

        return BaseResponse(result.noteId)
    }

    companion object {
        const val MERGED_TEXT_SEPARATOR = "\n---\n\n"
        const val MERGED_CAPTION_SEPARATOR = "\n"
    }

}
