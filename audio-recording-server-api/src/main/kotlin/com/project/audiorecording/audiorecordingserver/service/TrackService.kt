package com.project.audiorecording.audiorecordingserver.service

import com.project.audiorecording.audiorecordingserver.domain.dto.DiscDto
import com.project.audiorecording.audiorecordingserver.domain.dto.TrackDto
import com.project.audiorecording.audiorecordingserver.domain.entity.Disc
import com.project.audiorecording.audiorecordingserver.domain.entity.Track
import com.project.audiorecording.audiorecordingserver.mapper.IMapper
import com.project.audiorecording.audiorecordingserver.repository.TrackRepository
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class TrackService(
    private val trackRepository: TrackRepository,
    private val trackMapper: IMapper<Track, TrackDto, UUID>,
    private val discService: CrudService<Disc, DiscDto, UUID>
)
    : CrudService<Track, TrackDto, UUID>
{
    override fun create(dto: TrackDto): Track {
        val track = getEntity(dto)
        return trackRepository.save(track)
    }

    override fun read(id: UUID): TrackDto {
        return trackMapper.toDto(requireOne(id))
    }

    override fun update(dto: TrackDto): Track {
        requireOne(dto.id!!)
        val updatedTrack = getEntity(dto)
        return trackRepository.save(updatedTrack)
    }

    override fun delete(id: UUID) {
        removeEntity(id)
        trackRepository.deleteById(id)
    }

    override fun getAll(): List<TrackDto> {
        return trackMapper.ToDtoList(trackRepository.findAll())!!
    }

    override fun requireOne(id: UUID): Track {
        return trackRepository.findById(id).orElseThrow(
            { throw RuntimeException("Track with id $id not found") }
        )
    }

    private fun getEntity(dto: TrackDto): Track {
        val disc = discService.requireOne(dto.disc!!.id!!)
        return Track(
            id = dto.id,
            title = dto.title,
            duration = dto.duration,
            disc = disc
        )
    }

    private fun removeEntity(id: UUID) {
        requireOne(id)
    }
}