package ru.microsrv.teamretroservice.model.dto

import com.fasterxml.jackson.annotation.JsonInclude
import java.util.*


/**
 * DTO пользователя ретро.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
data class UserDto(

    val userId: UUID,
    val login: String,
//    val passwordHash: String,
//    val pepper: String,
    val name: String,
//    val role: String
)
