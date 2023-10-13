package com.project.audiorecording.audiorecordingserver.mapper

import com.project.audiorecording.audiorecordingserver.domain.dto.PopCompositionDto
import com.project.audiorecording.audiorecordingserver.domain.entity.PopComposition
import org.mapstruct.*
import org.springframework.stereotype.Component
import java.util.*

@Component
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
class PopCompositionMapper : IMapper<PopComposition, PopCompositionDto, UUID> {
    override fun toEntity(dto: PopCompositionDto, foundEntity: PopComposition): PopComposition {
        val popComposition = PopComposition(
            genre = dto.genre,
            popularity = dto.popularity
        )
        popComposition.id = foundEntity.id
        popComposition.title = dto.title
        popComposition.author = dto.author
        popComposition.duration = dto.duration
        popComposition.disc = foundEntity.disc

        return popComposition
    }

    override fun toDto(entity: PopComposition): PopCompositionDto {
        val popComposition =  PopCompositionDto(
            genre = entity.genre,
            popularity = entity.popularity

        )
        popComposition.id = entity.id
        popComposition.title = entity.title
        popComposition.author = entity.author
        popComposition.duration = entity.duration

        return popComposition
    }

    override fun ToDtoList(entities: List<PopComposition>?): List<PopCompositionDto>? {
        return entities?.map { toDto(it) }
    }

    override fun toDtoMap(entities: List<PopComposition>?): Map<UUID, PopCompositionDto>? {
        return entities?.associateBy({ it.id!! }, { toDto(it) })
    }

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    override fun partialUpdate(discDto: PopCompositionDto, disc: PopComposition): PopComposition {
        val popComposition = PopComposition(
            genre = discDto.genre ?: disc.genre,
            popularity = discDto.popularity ?: disc.popularity
        )

        popComposition.id = discDto.id ?: disc.id
        popComposition.title = discDto.title ?: disc.title
        popComposition.author = discDto.author ?: disc.author
        popComposition.duration = discDto.duration ?: disc.duration
        popComposition.disc = disc.disc

        return popComposition
    }
}