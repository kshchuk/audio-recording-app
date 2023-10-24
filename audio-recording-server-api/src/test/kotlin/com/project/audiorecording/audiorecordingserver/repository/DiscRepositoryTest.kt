package com.project.audiorecording.audiorecordingserver.repository

import com.project.audiorecording.audiorecordingserver.domain.entity.Disc
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.beans.factory.annotation.Autowired
import java.time.Duration
import java.util.UUID

@DataJpaTest
class DiscRepositoryTest {

    @Autowired
    private lateinit var discRepository: DiscRepository

    @Test
    fun testFindById() {
        // Create and save a sample Disc
        val disc = Disc(UUID.fromString("sample-uuid"), "Test Disc", 10, null, Duration.ofMinutes(45))
        discRepository.save(disc)

        val savedDisc = discRepository.findById(UUID.fromString("sample-uuid"))

        assertTrue(savedDisc.isPresent)
        assertEquals("Test Disc", savedDisc.get().name)
    }
}
