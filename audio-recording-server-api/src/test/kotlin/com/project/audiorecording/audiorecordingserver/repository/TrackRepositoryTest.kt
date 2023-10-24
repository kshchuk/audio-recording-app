package com.project.audiorecording.audiorecordingserver.repository

import com.project.audiorecording.audiorecordingserver.domain.entity.Track
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.BeforeEach
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.beans.factory.annotation.Autowired
import java.time.Duration
import java.util.UUID

@DataJpaTest
class TrackRepositoryTest {

    @Autowired
    private lateinit var trackRepository: TrackRepository

    @BeforeEach
    fun setup() {
        // Create and save a sample Track
        val track = Track(UUID.randomUUID(), "Test Track", "Test Author", null, Duration.ofMinutes(5))
        trackRepository.save(track)
    }

    @Test
    fun testFindById() {
        val savedTrack = trackRepository.findById(UUID.fromString("sample-uuid"))

        assertTrue(savedTrack.isPresent)
        assertEquals("Test Track", savedTrack.get().title)
    }
}
