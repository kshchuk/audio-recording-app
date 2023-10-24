package com.project.audiorecording.audiorecordingserver.mapper

import com.project.audiorecording.audiorecordingserver.domain.dto.DiscDto
import com.project.audiorecording.audiorecordingserver.domain.entity.Disc
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.BeforeEach
import org.springframework.boot.test.context.SpringBootTest
import java.time.Duration
import java.util.UUID

@SpringBootTest
class DiscMapperTest {

    private lateinit var discMapper: DiscMapper

    @BeforeEach
    fun setup() {
        discMapper = DiscMapper()
    }

    @Test
    fun testToEntity() {
        val discDto = DiscDto(UUID.randomUUID(), "Test Disc", 10, Duration.ofMinutes(45))
        val discEntity = Disc()

        val resultEntity = discMapper.toEntity(discDto, discEntity)

        assertEquals(discDto.id, resultEntity.id)
        assertEquals(discDto.name, resultEntity.name)
        assertEquals(discDto.trackNumber, resultEntity.trackNumber)
        assertEquals(discDto.totalDuration, resultEntity.totalDuration)
    }

    @Test
    fun testToDto() {
        val discEntity = Disc()
        discEntity.id = UUID.randomUUID()
        discEntity.name = "Test Disc"
        discEntity.trackNumber = 10
        discEntity.totalDuration = Duration.ofMinutes(45)

        val discDto = discMapper.toDto(discEntity)

        assertEquals(discEntity.id, discDto.id)
        assertEquals(discEntity.name, discDto.name)
        assertEquals(discEntity.trackNumber, discDto.trackNumber)
        assertEquals(discEntity.totalDuration, discDto.totalDuration)
    }
}
