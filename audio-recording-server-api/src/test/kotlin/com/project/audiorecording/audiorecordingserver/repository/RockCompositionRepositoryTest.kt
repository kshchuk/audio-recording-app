package com.project.audiorecording.audiorecordingserver.repository

import com.project.audiorecording.audiorecordingserver.domain.entity.RockComposition
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.beans.factory.annotation.Autowired
import java.util.UUID

@DataJpaTest
class RockCompositionRepositoryTest {

    @Autowired
    private lateinit var rockCompositionRepository: RockCompositionRepository

    @Test
    fun testFindById() {
        // Create and save a sample Rock Composition
        val rockComposition = RockComposition("Rock")
        rockComposition.id = UUID.fromString("sample-uuid")
        rockCompositionRepository.save(rockComposition)

        val savedRockComposition = rockCompositionRepository.findById(UUID.fromString("sample-uuid"))

        assertTrue(savedRockComposition.isPresent)
        assertEquals("Rock", savedRockComposition.get().style)
    }
}
