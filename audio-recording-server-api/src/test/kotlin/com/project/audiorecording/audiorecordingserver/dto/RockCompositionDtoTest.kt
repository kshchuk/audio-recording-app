package com.project.audiorecording.audiorecordingserver.dto

import com.project.audiorecording.audiorecordingserver.domain.dto.RockCompositionDto
import com.project.audiorecording.audiorecordingserver.domain.dto.TrackDto
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNull
import org.junit.jupiter.api.Test
import java.util.UUID

class RockCompositionDtoTest {

    @Test
    fun testRockCompositionDtoWithTrack() {
        val track = TrackDto(UUID.randomUUID(), "Test Track", "Test Author", null, null)
        val style = "Rock Style"

        val rockCompositionDto = RockCompositionDto(track, style)

        assertEquals(track.id, rockCompositionDto.id)
        assertEquals(track.title, rockCompositionDto.title)
        assertEquals(track.author, rockCompositionDto.author)
        assertEquals(track.disc, rockCompositionDto.disc)
        assertNull(rockCompositionDto.duration)
        assertEquals(style, rockCompositionDto.style)
    }
}
