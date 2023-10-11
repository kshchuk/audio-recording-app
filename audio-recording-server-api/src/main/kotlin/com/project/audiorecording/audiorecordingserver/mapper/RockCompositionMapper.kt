package com.project.audiorecording.audiorecordingserver.mapper

import com.project.audiorecording.audiorecordingserver.domain.dto.RockCompositionDto
import com.project.audiorecording.audiorecordingserver.domain.entity.RockComposition
import org.mapstruct.*
import java.util.*


@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
class RockCompositionMapper : IMapper<RockComposition, RockCompositionDto, UUID> {
    override fun toEntity(dto: RockCompositionDto, foundEntity: RockComposition): RockComposition {
        val rockComposition = RockComposition(
            style = dto.style
        )
        rockComposition.id = foundEntity.id
        rockComposition.title = dto.title
        rockComposition.author = dto.author
        rockComposition.duration = dto.duration
        rockComposition.disc = foundEntity.disc

        return rockComposition
    }

    override fun toDto(entity: RockComposition): RockCompositionDto {
        val rockComposition =  RockCompositionDto(
            style = entity.style
        )
        rockComposition.id = entity.id
        rockComposition.title = entity.title
        rockComposition.author = entity.author
        rockComposition.duration = entity.duration

        return rockComposition
    }

    override fun ToDtoList(entities: List<RockComposition>?): List<RockCompositionDto>? {
        return entities?.map { toDto(it) }
    }

    override fun toDtoMap(entities: List<RockComposition>?): Map<UUID, RockCompositionDto>? {
        return entities?.associateBy({ it.id!! }, { toDto(it) })
    }

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    override fun partialUpdate(discDto: RockCompositionDto, disc: RockComposition): RockComposition {
        val rockComposition = RockComposition(
            style = discDto.style ?: disc.style
        )

        rockComposition.id = discDto.id ?: disc.id
        rockComposition.title = discDto.title ?: disc.title
        rockComposition.author = discDto.author ?: disc.author
        rockComposition.duration = discDto.duration ?: disc.duration
        rockComposition.disc = disc.disc

        return rockComposition
    }
}