package com.project.audiorecording.audiorecordingserver.service

import java.util.*

import com.project.audiorecording.audiorecordingserver.domain.dto.DiscDto
import com.project.audiorecording.audiorecordingserver.domain.dto.TrackDto
import com.project.audiorecording.audiorecordingserver.domain.entity.Disc
import com.project.audiorecording.audiorecordingserver.domain.entity.Track
import com.project.audiorecording.audiorecordingserver.mapper.IMapper
import com.project.audiorecording.audiorecordingserver.repository.DiscRepository
import org.springframework.stereotype.Service
import java.time.Duration

@Service
class DiscService(
    private val discRepository: DiscRepository,
    private val discMapper: IMapper<Disc, DiscDto, UUID>,
    private val trackMapper: IMapper<Track, TrackDto, UUID>,
    private val trackService: CrudService<Track, TrackDto, UUID>
) : CrudService<Disc, DiscDto, UUID>   {

    override fun create(dto: DiscDto): DiscDto {
        val disc = discMapper.toEntity(dto, Disc())
        val tracks = dto.tracks?.map { trackMapper.toEntity(trackService.create(it), Track()) }
        disc.tracks = tracks?.toMutableList()
        return discMapper.toDto(discRepository.save(disc))
    }

    override fun read(id: UUID): DiscDto {
        return discMapper.toDto(requireOne(id))
    }

    override fun update(dto: DiscDto): DiscDto {
        val disc = requireOne(dto.id!!)
        val updatedDisc = discMapper.toEntity(dto, disc)
        val tracks = dto.tracks?.map { trackMapper.toEntity(trackService.create(it), Track()) }
        updatedDisc.tracks = tracks?.toMutableList()
        return discMapper.toDto(discRepository.save(disc))
    }

    override fun delete(id: UUID) {
        requireOne(id)
        discRepository.deleteById(id)
    }

    override fun getAll(): List<DiscDto> {
        return discMapper.ToDtoList(discRepository.findAll())!!
    }

    fun getAllTracks(discId: UUID) : List<TrackDto> {
        val disc = requireOne(discId)
        return discMapper.toDto(disc).tracks!!
    }

    override fun requireOne(id: UUID): Disc {
        return discRepository.findById(id).orElseThrow(
            { throw RuntimeException("Disc with id $id not found") }
        )
    }

    fun insertTrack(disc: UUID, track: Track): DiscDto {
        val discEntity = requireOne(disc)
        discEntity.tracks?.add(track)
        return discMapper.toDto(discRepository.save(discEntity))
    }

    fun calculateTotalDuration(disc: UUID): Duration {
        val discEntity = requireOne(disc)
        return discEntity.tracks?.map { it.duration }?.reduce { acc, duration -> acc?.plus(duration) }!!
    }

    fun findSongsByLength(disc: UUID, min: Duration, max: Duration) : List<TrackDto> {
        val discEntity = requireOne(disc)
        return discEntity.tracks?.filter { it.duration?.seconds!! >= min.seconds &&
            it.duration?.seconds!! <= max.seconds }?.map { trackMapper.toDto(it) }!!
    }

    // Rearrange the songs on the disc based on style affiliation which is
    // specified in the subclass of the track class
    fun sortSongsByStyle(disc: UUID) : List<TrackDto> {
        val discEntity = requireOne(disc)
        return discEntity.tracks?.sortedBy { it::class.simpleName }?.map { trackMapper.toDto(it) }!!
    }
}