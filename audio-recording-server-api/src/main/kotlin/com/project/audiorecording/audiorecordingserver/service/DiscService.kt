package com.project.audiorecording.audiorecordingserver.service

import java.util.*

import com.project.audiorecording.audiorecordingserver.domain.dto.DiscDto
import com.project.audiorecording.audiorecordingserver.domain.dto.TrackDto
import com.project.audiorecording.audiorecordingserver.domain.entity.Disc
import com.project.audiorecording.audiorecordingserver.domain.entity.Track
import com.project.audiorecording.audiorecordingserver.mapper.IMapper
import com.project.audiorecording.audiorecordingserver.repository.DiscRepository
import org.springframework.stereotype.Service

@Service
class DiscService(
    private val discRepository: DiscRepository,
    private val discMapper: IMapper<Disc, DiscDto, UUID>,
    private val trackService: CrudService<Track, TrackDto, UUID>
) : CrudService<Disc, DiscDto, UUID>   {

    override fun create(dto: DiscDto): Disc {
        val disc = discMapper.toEntity(dto, Disc())
        val tracks = dto.tracks?.map { trackService.create(it) }
        disc.tracks = tracks?.toMutableList()
        return discRepository.save(disc)
    }

    override fun read(id: UUID): Disc {
        return requireOne(id)
    }

    override fun update(dto: DiscDto): Disc {
        val disc = requireOne(dto.id!!)
        val updatedDisc = discMapper.toEntity(dto, disc)
        val tracks = dto.tracks?.map { trackService.update(it) }
        updatedDisc.tracks = tracks?.toMutableList()
        return discRepository.save(updatedDisc)
    }

    override fun delete(id: UUID) {
        discRepository.deleteById(id)
    }

    override fun getAll(): List<Disc> {
        return discRepository.findAll()
    }

    override fun requireOne(id: UUID): Disc {
        return discRepository.findById(id).orElseThrow(
            { throw RuntimeException("Disc with id $id not found") }
        )
    }

    fun calculateTotalDuration(disc: Disc): Disc {
        val totalDuration = disc.tracks?.map { it.duration }?.reduce { acc, duration -> acc!!.plus(duration) }
        disc.totalDuration = totalDuration
        update(discMapper.toDto(disc))
        return disc
    }
}