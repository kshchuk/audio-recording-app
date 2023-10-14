package com.project.audiorecording.audiorecordingserver.service

import com.project.audiorecording.audiorecordingserver.domain.dto.DiscDto
import com.project.audiorecording.audiorecordingserver.domain.dto.TrackDto
import com.project.audiorecording.audiorecordingserver.domain.entity.*
import com.project.audiorecording.audiorecordingserver.mapper.ClassicalCompositionMapper
import com.project.audiorecording.audiorecordingserver.mapper.IMapper
import com.project.audiorecording.audiorecordingserver.mapper.PopCompositionMapper
import com.project.audiorecording.audiorecordingserver.mapper.RockCompositionMapper
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
    private val popCompositionMapper: PopCompositionMapper,
    private val rockCompositionMapper: RockCompositionMapper,
    private val classicalCompositionMapper: ClassicalCompositionMapper,
)
    : ITrackService
{
    override fun create(dto: TrackDto): TrackDto {
        val track = getEntity(dto)
        val trackDto =  toDto(trackRepository.save(track))
        trackDto.disc = discMapper.toDto(track.disc!!)
        updateDisc(track.disc!!.id!!)
        return trackDto
    }

    override fun read(id: UUID): TrackDto {
        return toDto(requireOne(id))
    }

    override fun update(dto: TrackDto): TrackDto {
        requireOne(dto.id!!)
        val updatedTrack = getEntity(dto)
        val trackDto = toDto(trackRepository.save(updatedTrack))
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
        val tracks = trackRepository.findAll()
        return getDtos(tracks)
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

    fun toDto(track: Track): TrackDto {
        val trackDto: TrackDto

        if (track is PopComposition) {
            trackDto = popCompositionMapper.toDto(track)
        } else if (track is RockComposition) {
            trackDto = rockCompositionMapper.toDto(track)
        } else if (track is ClassicalComposition) {
            trackDto = classicalCompositionMapper.toDto(track)
        } else {
            trackDto = trackMapper.toDto(track)
        }

        return trackDto
    }

    fun getDtos(tracks: List<Track>): List<TrackDto> {
        val tracksDtos = mutableListOf<TrackDto>()

        for (track in tracks) {
            tracksDtos.add(toDto(track))
        }

        return tracksDtos
    }
}