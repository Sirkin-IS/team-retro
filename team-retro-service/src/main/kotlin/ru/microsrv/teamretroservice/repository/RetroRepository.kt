package ru.microsrv.teamretroservice.repository

import java.util.*
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import ru.microsrv.teamretroservice.model.entity.RetroEntity

/**
 * Репозиторий для работы с ретро.
 */
@Repository
interface RetroRepository : JpaRepository<RetroEntity, UUID>
