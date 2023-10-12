package com.project.audiorecording.audiorecordingserver.service

import com.project.audiorecording.audiorecordingserver.domain.dto.DiscDto
import com.project.audiorecording.audiorecordingserver.domain.dto.TrackDto
import com.project.audiorecording.audiorecordingserver.domain.entity.Disc
import com.project.audiorecording.audiorecordingserver.domain.entity.Track
import com.project.audiorecording.audiorecordingserver.mapper.IMapper
import com.project.audiorecording.audiorecordingserver.repository.TrackRepository
import org.springframework.stereotype.Service
import java.util.UUID
import javax.swing.text.html.parser.Entity

@Service
class TrackService(
    private val trackRepository: TrackRepository,
    private val trackMapper: IMapper<Track, TrackDto, UUID>,
    private val discService: DiscService
)
    : CrudService<Track, TrackDto, UUID>
{
    override fun create(dto: TrackDto): TrackDto {
        val track = getEntity(dto)
        val trackDto =  trackMapper.toDto(trackRepository.save(track))
        updateDisc(trackDto.disc!!.id!!)
        return trackDto
    }

    override fun read(id: UUID): TrackDto {
        return trackMapper.toDto(requireOne(id))
    }

    override fun update(dto: TrackDto): TrackDto {
        requireOne(dto.id!!)
        val updatedTrack = getEntity(dto)
        val trackDto = trackMapper.toDto(trackRepository.save(updatedTrack))
        updateDisc(trackDto.disc!!.id!!)
        return trackDto
    }

    override fun delete(id: UUID) {
        val trackDto = read(id)
        removeEntity(id)
        trackRepository.deleteById(id)
        updateDisc(trackDto.disc!!.id!!)
    }

    override fun getAll(): List<TrackDto> {
        return trackMapper.ToDtoList(trackRepository.findAll())!!
    }

    override fun requireOne(id: UUID): Track {
        return trackRepository.findById(id).orElseThrow(
            { throw RuntimeException("Track with id $id not found") }
        )
    }

    fun getEntity(dto: TrackDto): Track {
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

    fun updateDisc(discId: UUID) {
        discService.updateDisc(discId)
    }
}