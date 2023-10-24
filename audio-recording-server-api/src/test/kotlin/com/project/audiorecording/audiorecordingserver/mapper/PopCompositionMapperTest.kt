package com.project.audiorecording.audiorecordingserver.mapper

import com.project.audiorecording.audiorecordingserver.domain.dto.PopCompositionDto
import com.project.audiorecording.audiorecordingserver.domain.entity.PopComposition
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.BeforeEach
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class PopCompositionMapperTest {

    private lateinit var popCompositionMapper: PopCompositionMapper

    @BeforeEach
    fun setup() {
        popCompositionMapper = PopCompositionMapper()
    }

    @Test
    fun testToEntity() {
        val popCompositionDto = PopCompositionDto("Pop", 100)
        val popCompositionEntity = PopComposition()

        val resultEntity = popCompositionMapper.toEntity(popCompositionDto, popCompositionEntity)

        assertEquals(popCompositionDto.genre, resultEntity.genre)
        assertEquals(popCompositionDto.popularity, resultEntity.popularity)
    }

    @Test
    fun testToDto() {
        val popCompositionEntity = PopComposition()
        popCompositionEntity.genre = "Pop"
        popCompositionEntity.popularity = 100

        val popCompositionDto = popCompositionMapper.toDto(popCompositionEntity)

        assertEquals(popCompositionEntity.genre, popCompositionDto.genre)
        assertEquals(popCompositionEntity.popularity, popCompositionDto.popularity)
    }
}
