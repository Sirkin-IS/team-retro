package ru.microsrv.teamretroservice.mapper

import org.mapstruct.Mapper
import org.mapstruct.Mapping
import org.mapstruct.Mappings
import ru.microsrv.teamretroservice.model.dto.RetroDto
import ru.microsrv.teamretroservice.model.entity.RetroEntity
import ru.microsrv.teamretroservice.model.web.request.retro.CreateRetroRequest


/**
 * Маппер ретро.
 */
@Mapper
abstract class RetroMapper {

     /**
      * Маппинг CreateRetroRequest в RetroEntity.
      */
     @Mappings(
          value = [
               Mapping(expression = "java(java.time.ZonedDateTime.now())", target = "createDttm")
          ]
     )
     abstract fun toRetroEntity(request: CreateRetroRequest): RetroEntity

     abstract fun toRetroDto(request: RetroEntity?): RetroDto

     abstract fun toRetroDto(request: Iterable<RetroEntity>): List<RetroDto>
}
