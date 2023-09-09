package ru.microsrv.teamretroservice.controller

import jakarta.validation.Valid
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import ru.microsrv.teamretroservice.model.web.request.retro.CreateRetroRequest
import ru.microsrv.teamretroservice.model.web.request.retro.GetRetroListRequest
import ru.microsrv.teamretroservice.model.web.response.retro.BaseRetroResponse
import ru.microsrv.teamretroservice.model.web.response.retro.GetRetroListResponse
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
    fun createRetro(@RequestBody @Valid request: CreateRetroRequest): BaseRetroResponse {
        return retroService.createRetro(request)
    }

    /**
     * Получение списка ретро.
     */
    @GetMapping("list")
    fun getRetroList(@Valid request: GetRetroListRequest): GetRetroListResponse {
        return retroService.getRetroList(request)
    }

}
