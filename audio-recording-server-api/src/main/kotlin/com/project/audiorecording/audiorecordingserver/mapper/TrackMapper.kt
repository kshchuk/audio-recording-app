package com.project.audiorecording.audiorecordingserver.mapper

import com.project.audiorecording.audiorecordingserver.domain.dto.TrackDto
import com.project.audiorecording.audiorecordingserver.domain.entity.Track
import org.mapstruct.BeanMapping
import org.mapstruct.Mapper
import org.mapstruct.NullValuePropertyMappingStrategy
import org.mapstruct.ReportingPolicy
import org.springframework.stereotype.Component
import java.util.*

@Component
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
class TrackMapper : IMapper<Track, TrackDto, UUID> {
    override fun toEntity(dto: TrackDto, foundEntity: Track): Track {
        val track = Track(
            id = foundEntity.id,
            title = dto.title,
            duration = dto.duration,
            disc = foundEntity.disc,
            author = dto.author

        )
        return track
    }

    override fun toDto(entity: Track): TrackDto {
        val track = TrackDto(
            id = entity.id,
            title = entity.title,
            duration = entity.duration,
            disc = entity.disc!!.let { DiscMapper().toDto(it) }
        )
        return track
    }

    override fun ToDtoList(entities: List<Track>?): List<TrackDto>? {
        return entities?.map { toDto(it) }
    }

    override fun toDtoMap(entities: List<Track>?): Map<UUID, TrackDto>? {
        return entities?.associateBy({ it.id!! }, { toDto(it) })
    }

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    override fun partialUpdate(discDto: TrackDto, disc: Track): Track {
        val track = Track(
            id = discDto.id ?: disc.id,
            title = discDto.title ?: disc.title,
            duration = discDto.duration ?: disc.duration,
            disc = disc.disc
        )
        return track
    }
}