package com.project.audiorecording.audiorecordingserver.mapper

import com.project.audiorecording.audiorecordingserver.domain.dto.DiscDto
import com.project.audiorecording.audiorecordingserver.domain.entity.Disc
import org.mapstruct.*
import java.util.UUID

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
class DiscMapper : IMapper<Disc, DiscDto, UUID> {
    override fun toEntity(dto: DiscDto, foundEntity: Disc): Disc {
        return Disc(
            id = foundEntity.id,
            name = dto.name,
            trackNumber = dto.trackNumber,
            totalDuration = dto.totalDuration,
            tracks = foundEntity.tracks
        )
    }

    override fun toDto(entity: Disc): DiscDto {
        return DiscDto(
            id = entity.id,
            name = entity.name,
            trackNumber = entity.trackNumber,
            totalDuration = entity.totalDuration,
            tracks = entity.tracks?.map { TrackMapper().toDto(it) }
        )
    }

    override fun ToDtoList(entities: List<Disc>?): List<DiscDto>? {
        return entities?.map { toDto(it) }
    }

    override fun toDtoMap(entities: List<Disc>?): Map<UUID, DiscDto>? {
        return entities?.associateBy({ it.id!! }, { toDto(it) })
    }

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    override fun partialUpdate(discDto: DiscDto, disc: Disc): Disc {
        return Disc(
            id = discDto.id ?: disc.id,
            name = discDto.name ?: disc.name,
            trackNumber = discDto.trackNumber ?: disc.trackNumber,
            totalDuration = discDto.totalDuration ?: disc.totalDuration,
            tracks = disc.tracks
        )
    }
}