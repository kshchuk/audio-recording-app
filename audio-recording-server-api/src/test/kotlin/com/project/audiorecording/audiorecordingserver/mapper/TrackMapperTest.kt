package com.project.audiorecording.audiorecordingserver.mapper

import com.project.audiorecording.audiorecordingserver.domain.dto.TrackDto
import com.project.audiorecording.audiorecordingserver.domain.entity.Track
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.BeforeEach
import org.springframework.boot.test.context.SpringBootTest
import java.time.Duration
import java.util.UUID

@SpringBootTest
class TrackMapperTest {

    private lateinit var trackMapper: TrackMapper

    @BeforeEach
    fun setup() {
        trackMapper = TrackMapper()
    }

    @Test
    fun testToEntity() {
        val trackDto = TrackDto(UUID.randomUUID(), "Test Track", "Test Author", null, Duration.ofMinutes(5))
        val trackEntity = Track()

        val resultEntity = trackMapper.toEntity(trackDto, trackEntity)

        assertEquals(trackDto.id, resultEntity.id)
        assertEquals(trackDto.title, resultEntity.title)
        assertEquals(trackDto.author, resultEntity.author)
        assertEquals(trackDto.duration, resultEntity.duration)
    }

    @Test
    fun testToDto() {
        val trackEntity = Track()
        trackEntity.id = UUID.randomUUID()
        trackEntity.title = "Test Track"
        trackEntity.author = "Test Author"
        trackEntity.duration = Duration.ofMinutes(5)

        val trackDto = trackMapper.toDto(trackEntity)

        assertEquals(trackEntity.id, trackDto.id)
        assertEquals(trackEntity.title, trackDto.title)
        assertEquals(trackEntity.author, trackDto.author)
        assertEquals(trackEntity.duration, trackDto.duration)
    }
}
