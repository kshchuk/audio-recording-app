package com.project.audiorecording.audiorecordingserver.repository

import com.project.audiorecording.audiorecordingserver.domain.entity.PopComposition
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.beans.factory.annotation.Autowired
import java.util.UUID

@DataJpaTest
class PopCompositionRepositoryTest {

    @Autowired
    private lateinit var popCompositionRepository: PopCompositionRepository

    @Test
    fun testFindById() {
        // Create and save a sample Pop Composition
        val popComposition = PopComposition("Pop", 80)
        popComposition.id = UUID.fromString("sample-uuid")
        popCompositionRepository.save(popComposition)

        val savedPopComposition = popCompositionRepository.findById(UUID.fromString("sample-uuid"))

        assertTrue(savedPopComposition.isPresent)
        assertEquals("Pop", savedPopComposition.get().genre)
        assertEquals(80, savedPopComposition.get().popularity)
    }
}
