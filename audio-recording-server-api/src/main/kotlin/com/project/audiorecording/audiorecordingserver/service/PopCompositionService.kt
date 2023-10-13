package com.project.audiorecording.audiorecordingserver.service

import com.project.audiorecording.audiorecordingserver.domain.dto.PopCompositionDto
import com.project.audiorecording.audiorecordingserver.domain.entity.PopComposition
import com.project.audiorecording.audiorecordingserver.mapper.PopCompositionMapper
import com.project.audiorecording.audiorecordingserver.repository.PopCompositionRepository
import lombok.RequiredArgsConstructor
import org.springframework.stereotype.Service
import java.util.UUID

/**
 * Service for {@link PopComposition}
 */
@Service
@RequiredArgsConstructor
class PopCompositionService(
    private val popCompositionRepository: PopCompositionRepository,
    private val popCompositionMapper: PopCompositionMapper,
    private val trackService: TrackService,
) : CrudService<PopComposition, PopCompositionDto, UUID> {
    override fun create(dto: PopCompositionDto): PopCompositionDto {
        val popComposition = getEntity(dto)
        val popDto = popCompositionMapper.toDto(popCompositionRepository.save(popComposition))
        trackService.updateDisc(popDto.disc!!.id!!)
        return popDto
    }

    override fun read(id: UUID): PopCompositionDto {
        return popCompositionMapper.toDto(requireOne(id))
    }

    override fun update(dto: PopCompositionDto): PopCompositionDto {
        requireOne(dto.id!!)
        val updatedPopComposition = getEntity(dto)
        val popDto = popCompositionMapper.toDto(popCompositionRepository.save(updatedPopComposition))
        trackService.updateDisc(popDto.disc!!.id!!)
        return popDto
    }

    override fun delete(id: UUID) {
        val popDto = read(id)
        popCompositionRepository.deleteById(id)
        trackService.updateDisc(popDto.disc!!.id!!)
    }

    override fun getAll(): List<PopCompositionDto> {
        return popCompositionMapper.ToDtoList(popCompositionRepository.findAll())!!
    }

    override fun requireOne(id: UUID): PopComposition {
        return popCompositionRepository.findById(id).orElseThrow(
            { throw RuntimeException("PopComposition with id $id not found") }
        )
    }

    private fun getEntity(dto: PopCompositionDto): PopComposition {
        val track = trackService.getEntity(dto)
        val popComposition = PopComposition(
            genre = dto.genre,
            popularity = dto.popularity
        )
        popComposition.id = track.id
        popComposition.title = track.title
        popComposition.author = track.author
        popComposition.duration = track.duration
        popComposition.disc = track.disc

        return popComposition
    }
}