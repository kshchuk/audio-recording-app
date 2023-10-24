package com.project.audiorecording.audiorecordingserver.dto

import com.project.audiorecording.audiorecordingserver.domain.dto.ClassicalCompositionDto
import com.project.audiorecording.audiorecordingserver.domain.dto.TrackDto
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNull
import org.junit.jupiter.api.Test
import java.util.UUID

class ClassicalCompositionDtoTest {

    @Test
    fun testClassicalCompositionDtoWithTrack() {
        val track = TrackDto(UUID.randomUUID(), "Test Track", "Test Author", null, null)
        val epoch = "Baroque"

        val classicalCompositionDto = ClassicalCompositionDto(track, epoch)

        assertEquals(track.id, classicalCompositionDto.id)
        assertEquals(track.title, classicalCompositionDto.title)
        assertEquals(track.author, classicalCompositionDto.author)
        assertEquals(track.disc, classicalCompositionDto.disc)
        assertNull(classicalCompositionDto.duration)
        assertEquals(epoch, classicalCompositionDto.epoch)
    }
}
