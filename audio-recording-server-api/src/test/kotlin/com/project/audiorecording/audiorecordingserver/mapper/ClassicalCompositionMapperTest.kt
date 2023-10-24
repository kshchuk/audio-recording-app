package com.project.audiorecording.audiorecordingserver.mapper

import com.project.audiorecording.audiorecordingserver.domain.dto.ClassicalCompositionDto
import com.project.audiorecording.audiorecordingserver.domain.entity.ClassicalComposition
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.BeforeEach
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class ClassicalCompositionMapperTest {

    private lateinit var classicalCompositionMapper: ClassicalCompositionMapper

    @BeforeEach
    fun setup() {
        classicalCompositionMapper = ClassicalCompositionMapper()
    }

    @Test
    fun testToEntity() {
        val classicalCompositionDto = ClassicalCompositionDto("Baroque")
        val classicalCompositionEntity = ClassicalComposition()

        val resultEntity = classicalCompositionMapper.toEntity(classicalCompositionDto, classicalCompositionEntity)

        assertEquals(classicalCompositionDto.epoch, resultEntity.epoch)
    }

    @Test
    fun testToDto() {
        val classicalCompositionEntity = ClassicalComposition()
        classicalCompositionEntity.epoch = "Baroque"

        val classicalCompositionDto = classicalCompositionMapper.toDto(classicalCompositionEntity)

        assertEquals(classicalCompositionEntity.epoch, classicalCompositionDto.epoch)
    }
}
