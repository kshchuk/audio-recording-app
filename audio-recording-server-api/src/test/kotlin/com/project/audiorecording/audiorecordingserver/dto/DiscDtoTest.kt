package com.project.audiorecording.audiorecordingserver.dto


import com.project.audiorecording.audiorecordingserver.domain.dto.DiscDto
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.time.Duration
import java.util.UUID

class DiscDtoTest {

    @Test
    fun testDiscDtoProperties() {
        val id = UUID.randomUUID()
        val name = "Test Disc"
        val trackNumber = 10
        val totalDuration = Duration.ofMinutes(50)

        val discDto = DiscDto(id, name, trackNumber, totalDuration)

        assertEquals(id, discDto.id)
        assertEquals(name, discDto.name)
        assertEquals(trackNumber, discDto.trackNumber)
        assertEquals(totalDuration, discDto.totalDuration)
    }
}
