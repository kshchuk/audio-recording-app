package com.project.audiorecording.audiorecordingserver.service

import com.project.audiorecording.audiorecordingserver.domain.dto.DiscDto
import com.project.audiorecording.audiorecordingserver.domain.dto.TrackDto
import com.project.audiorecording.audiorecordingserver.domain.entity.Disc
import com.project.audiorecording.audiorecordingserver.domain.entity.Track
import com.project.audiorecording.audiorecordingserver.mapper.IMapper
import com.project.audiorecording.audiorecordingserver.repository.TrackRepository
import lombok.RequiredArgsConstructor
import org.springframework.context.annotation.Lazy
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

import java.util.UUID

@Service
@RequiredArgsConstructor
class TrackService(
    private val trackRepository: TrackRepository,
    private val trackMapper: IMapper<Track, TrackDto, UUID>,
    @Lazy
    private val discService: IDiscService,
    private val discMapper: IMapper<Disc, DiscDto, UUID>,
)
    : ITrackService
{
    override fun create(dto: TrackDto): TrackDto {
        val track = getEntity(dto)
        val trackDto =  trackMapper.toDto(trackRepository.save(track))
        trackDto.disc = discMapper.toDto(track.disc!!)
        updateDisc(track.disc!!.id!!)
        return trackDto
    }

    override fun read(id: UUID): TrackDto {
        return trackMapper.toDto(requireOne(id))
    }

    override fun update(dto: TrackDto): TrackDto {
        requireOne(dto.id!!)
        val updatedTrack = getEntity(dto)
        val trackDto = trackMapper.toDto(trackRepository.save(updatedTrack))
        trackDto.disc = discMapper.toDto(updatedTrack.disc!!)
        updateDisc(updatedTrack.disc!!.id!!)
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

    @Transactional
    fun getEntity(dto: TrackDto): Track {
        val disc = discService.requireOne(dto.disc!!.id!!)
        //var disc = discMapper.toEntity(dto.disc!!, Disc())
        //disc.id = dto.disc!!.id

        return Track(
            id = dto.id,
            title = dto.title,
            duration = dto.duration,
            author = dto.author,
            disc = disc
        )
    }

    private fun removeEntity(id: UUID) {
        requireOne(id)
    }

    override fun updateDisc(discId: UUID) {
        discService.updateDisc(discId)
    }
}