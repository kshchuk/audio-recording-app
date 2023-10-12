package com.project.audiorecording.audiorecordingserver.service

import com.project.audiorecording.audiorecordingserver.domain.dto.ClassicalCompositionDto
import com.project.audiorecording.audiorecordingserver.domain.entity.ClassicalComposition
import com.project.audiorecording.audiorecordingserver.mapper.ClassicalCompositionMapper
import com.project.audiorecording.audiorecordingserver.repository.ClassicalCompositionRepository
import org.springframework.stereotype.Service
import java.util.UUID

/**
 * Service for {@link ClassicalComposition}
 */
@Service
class ClassicalCompositionService(
    private val classicalCompositionRepository: ClassicalCompositionRepository,
    private val classicalCompositionMapper: ClassicalCompositionMapper,
    private val trackService: TrackService,
) : CrudService<ClassicalComposition, ClassicalCompositionDto, UUID> {
    override fun create(dto: ClassicalCompositionDto): ClassicalCompositionDto {
        val classicalComposition = getEntity(dto)
        return classicalCompositionMapper.toDto(classicalCompositionRepository.save(classicalComposition))
    }

    override fun read(id: UUID): ClassicalCompositionDto {
        return classicalCompositionMapper.toDto(requireOne(id))
    }

    override fun update(dto: ClassicalCompositionDto): ClassicalCompositionDto {
        requireOne(dto.id!!)
        val updatedClassicalComposition = getEntity(dto)
        return classicalCompositionMapper.toDto(classicalCompositionRepository.save(updatedClassicalComposition))
    }

    override fun delete(id: UUID) {
        requireOne(id)
        classicalCompositionRepository.deleteById(id)
    }

    override fun getAll(): List<ClassicalCompositionDto> {
        return classicalCompositionMapper.ToDtoList(classicalCompositionRepository.findAll())!!
    }

    override fun requireOne(id: UUID): ClassicalComposition {
        return classicalCompositionRepository.findById(id).orElseThrow(
            { throw RuntimeException("ClassicalComposition with id $id not found") }
        )
    }

    private fun getEntity(dto: ClassicalCompositionDto): ClassicalComposition {
        val track = trackService.getEntity(dto)
        val classicalComposition = ClassicalComposition(
            epoch = dto.epoch,
        )
        classicalComposition.id = track.id
        classicalComposition.title = track.title
        classicalComposition.author = track.author
        classicalComposition.duration = track.duration
        classicalComposition.disc = track.disc

        return classicalComposition
    }
}