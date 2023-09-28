package ru.microsrv.teamretroservice.repository

import java.util.*
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import ru.microsrv.teamretroservice.model.entity.RetroUserEntity


/**
 * Репозиторий пользователей.
 */
@Repository
interface UserRepository : CrudRepository<RetroUserEntity, UUID> {

    fun findFirstByLogin(login: String): RetroUserEntity?
}
