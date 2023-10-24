package com.project.audiorecording.audiorecordingserver.dto

import com.project.audiorecording.audiorecordingserver.domain.dto.PopCompositionDto
import com.project.audiorecording.audiorecordingserver.domain.dto.TrackDto
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNull
import org.junit.jupiter.api.Test
import java.util.UUID

class PopCompositionDtoTest {

    @Test
    fun testPopCompositionDtoWithTrack() {
        val track = TrackDto(UUID.randomUUID(), "Test Track", "Test Author", null, null)
        val genre = "Pop"
        val popularity = 5

        val popCompositionDto = PopCompositionDto(track, genre, popularity)

        assertEquals(track.id, popCompositionDto.id)
        assertEquals(track.title, popCompositionDto.title)
        assertEquals(track.author, popCompositionDto.author)
        assertEquals(track.disc, popCompositionDto.disc)
        assertNull(popCompositionDto.duration)
        assertEquals(genre, popCompositionDto.genre)
        assertEquals(popularity, popCompositionDto.popularity)
    }
}
