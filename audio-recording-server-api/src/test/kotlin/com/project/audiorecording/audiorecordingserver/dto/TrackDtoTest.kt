package com.project.audiorecording.audiorecordingserver.dto

import com.project.audiorecording.audiorecordingserver.domain.dto.TrackDto
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNull
import org.junit.jupiter.api.Test
import java.time.Duration
import java.util.UUID

class TrackDtoTest {

    @Test
    fun testTrackDtoProperties() {
        val id = UUID.randomUUID()
        val title = "Test Track"
        val author = "Test Author"
        val duration = Duration.ofMinutes(5)

        val trackDto = TrackDto(id, title, author, null, duration)

        assertEquals(id, trackDto.id)
        assertEquals(title, trackDto.title)
        assertEquals(author, trackDto.author)
        assertNull(trackDto.disc)
        assertEquals(duration, trackDto.duration)
    }
}
