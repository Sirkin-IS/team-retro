package ru.microsrv.teamretroservice.controller

import jakarta.validation.Valid
import java.util.UUID
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
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
import ru.microsrv.teamretroservice.service.RetroService


/**
 * Контроллер ретро.
 */
@RestController
@RequestMapping("api/v1/retro")
class RetroController(
    private val retroService: RetroService
) {

    /**
     * Создание нового ретро.
     */
    @PostMapping
    fun createRetro(@RequestBody @Valid request: CreateRetroRequest): BaseResponse {
        return retroService.createRetro(request)
    }

    /**
     * Получение списка ретро.
     */
    @GetMapping("list")
    fun getRetroList(@Valid request: GetRetroListRequest): GetRetroListResponse {
        return retroService.getRetroList(request)
    }

    /**
     * Получение параметров ретро.
     */
    @GetMapping("{retroId}")
    fun getRetro(@PathVariable retroId: UUID): GetRetroResponse {
        return retroService.getRetro(retroId)
    }

    /**
     * Обновление параметров ретро.
     */
    @PutMapping("{retroId}")
    fun updateRetro(@PathVariable retroId: UUID, @Valid @RequestBody request: UpdateRetroRequest): BaseResponse {
        return retroService.updateRetro(retroId, request)
    }

    /**
     * Удаление ретро.
     */
    @DeleteMapping("{retroId}")
    fun deleteRetro(@PathVariable retroId: UUID): TotalResponse {
        return retroService.deleteRetro(retroId)
    }

    /**
     * Создание новой заметки.
     */
    @PostMapping("{retroId}/note/create")
    fun createNote(@PathVariable retroId: UUID, @Valid @RequestBody request: CreateNoteRequest): BaseResponse {
        return retroService.createNote(retroId, request)
    }

    /**
     * Получение списка заметок ретро.
     */
    @GetMapping("{retroId}/note/list")
    fun getNoteList(@PathVariable retroId: UUID, @Valid request: GetNoteListRequest): GetNoteListResponse {
        return retroService.getNoteList(retroId, request)
    }

    /**
     * Обновление заметки.
     */
    @PutMapping("note/update")
    fun updateNote(@Valid @RequestBody request: UpdateNoteRequest): BaseResponse {
        return retroService.updateNote(request)
    }

    /**
     * Удаление списка заметок.
     */
    @DeleteMapping("note/delete")
    fun deleteNotes(@Valid @RequestBody request: DeleteNoteRequest): TotalResponse {
        return retroService.deleteNotes(request)
    }

    /**
     * Слияние списка заметок в одну.
     */
    @PutMapping("note/merge")
    fun mergeNote(@Valid @RequestBody request: MergeNoteRequest): BaseResponse {
        return retroService.mergeNote(request)
    }
}
