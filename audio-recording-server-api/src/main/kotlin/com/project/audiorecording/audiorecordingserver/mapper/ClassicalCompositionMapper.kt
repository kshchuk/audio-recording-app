package com.project.audiorecording.audiorecordingserver.mapper

import com.project.audiorecording.audiorecordingserver.domain.dto.ClassicalCompositionDto
import com.project.audiorecording.audiorecordingserver.domain.entity.ClassicalComposition
import org.mapstruct.*
import org.springframework.stereotype.Component
import java.util.*

@Component
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
class ClassicalCompositionMapper : IMapper<ClassicalComposition, ClassicalCompositionDto, UUID> {
    override fun toEntity(dto: ClassicalCompositionDto, foundEntity: ClassicalComposition): ClassicalComposition {
        val classicalComposition = ClassicalComposition(
            epoch = dto.epoch
        )
        classicalComposition.id = foundEntity.id
        classicalComposition.title = dto.title
        classicalComposition.author = dto.author
        classicalComposition.duration = dto.duration
        classicalComposition.disc = foundEntity.disc

        return classicalComposition
    }

    override fun toDto(entity: ClassicalComposition): ClassicalCompositionDto {
        val classicalComposition =  ClassicalCompositionDto(
            epoch = entity.epoch
        )
        classicalComposition.id = entity.id
        classicalComposition.title = entity.title
        classicalComposition.author = entity.author
        classicalComposition.duration = entity.duration

        return classicalComposition
    }

    override fun ToDtoList(entities: List<ClassicalComposition>?): List<ClassicalCompositionDto>? {
        return entities?.map { toDto(it) }
    }

    override fun toDtoMap(entities: List<ClassicalComposition>?): Map<UUID, ClassicalCompositionDto>? {
        return entities?.associateBy({ it.id!! }, { toDto(it) })
    }

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    override fun partialUpdate(discDto: ClassicalCompositionDto, disc: ClassicalComposition): ClassicalComposition {
        val classicalComposition = ClassicalComposition(
            epoch = discDto.epoch ?: disc.epoch
        )

        classicalComposition.id = discDto.id ?: disc.id
        classicalComposition.title = discDto.title ?: disc.title
        classicalComposition.author = discDto.author ?: disc.author
        classicalComposition.duration = discDto.duration ?: disc.duration
        classicalComposition.disc = disc.disc

        return classicalComposition
    }

}