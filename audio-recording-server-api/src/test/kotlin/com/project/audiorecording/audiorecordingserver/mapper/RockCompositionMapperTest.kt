package com.project.audiorecording.audiorecordingserver.mapper

import com.project.audiorecording.audiorecordingserver.domain.dto.RockCompositionDto
import com.project.audiorecording.audiorecordingserver.domain.entity.RockComposition
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.BeforeEach
import org.springframework.boot.test.context.SpringBootTest
import java.util.UUID

@SpringBootTest
class RockCompositionMapperTest {

    private lateinit var rockCompositionMapper: RockCompositionMapper

    @BeforeEach
    fun setup() {
        rockCompositionMapper = RockCompositionMapper()
    }

    @Test
    fun testToEntity() {
        val rockCompositionDto = RockCompositionDto("Rock")
        val rockCompositionEntity = RockComposition()

        val resultEntity = rockCompositionMapper.toEntity(rockCompositionDto, rockCompositionEntity)

        assertEquals(rockCompositionDto.style, resultEntity.style)
    }

    @Test
    fun testToDto() {
        val rockCompositionEntity = RockComposition()
        rockCompositionEntity.style = "Rock"

        val rockCompositionDto = rockCompositionMapper.toDto(rockCompositionEntity)

        assertEquals(rockCompositionEntity.style, rockCompositionDto.style)
    }
}
