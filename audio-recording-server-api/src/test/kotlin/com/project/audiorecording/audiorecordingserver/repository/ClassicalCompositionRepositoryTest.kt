package com.project.audiorecording.audiorecordingserver.repository

import com.project.audiorecording.audiorecordingserver.domain.entity.ClassicalComposition
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.beans.factory.annotation.Autowired
import java.util.UUID

@DataJpaTest
class ClassicalCompositionRepositoryTest {

    @Autowired
    private lateinit var classicalCompositionRepository: ClassicalCompositionRepository

    @Test
    fun testFindById() {
        // Create and save a sample Classical Composition
        val classicalComposition = ClassicalComposition("Baroque")
        classicalComposition.id = UUID.fromString("sample-uuid")
        classicalCompositionRepository.save(classicalComposition)

        val savedClassicalComposition = classicalCompositionRepository.findById(UUID.fromString("sample-uuid"))

        assertTrue(savedClassicalComposition.isPresent)
        assertEquals("Baroque", savedClassicalComposition.get().epoch)
    }
}
