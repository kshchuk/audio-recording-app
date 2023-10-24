package com.project.audiorecording.audiorecordingserver.domain.entity

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager
import java.time.Duration
import java.util.*

@DataJpaTest
class ClassicalCompositionEntityTests(@Autowired val entityManager: TestEntityManager) {

    @Test
    fun `When persisting a classical composition, it should be retrievable`() {
        val classicalComposition = ClassicalComposition(
            epoch = "Baroque"
        )
        classicalComposition.title = "Test Track"
        classicalComposition.author = "Test Author"
        classicalComposition.duration = Duration.ofMinutes(3)

        entityManager.persist(classicalComposition)
        entityManager.flush()

        val foundClassicalComposition = entityManager.find(ClassicalComposition::class.java, classicalComposition.id)
        assertNotNull(foundClassicalComposition)
        assertEquals(classicalComposition.title, foundClassicalComposition?.title)
        assertEquals(classicalComposition.author, foundClassicalComposition?.author)
        assertEquals(classicalComposition.duration, foundClassicalComposition?.duration)
        assertEquals(classicalComposition.epoch, foundClassicalComposition?.epoch)
    }

    @Test
    fun `When persisting a classical composition with a disc, the relationship should be retrievable`() {
        val disc = Disc(
            name = "Test Disc",
            trackNumber = 10,
            totalDuration = Duration.ofHours(1)
        )
        val classicalComposition = ClassicalComposition(
            epoch = "Baroque",
        )
        classicalComposition.title = "Test Track"
        classicalComposition.author = "Test Author"
        classicalComposition.duration = Duration.ofMinutes(3)
        classicalComposition.disc = disc


        entityManager.persist(disc)
        entityManager.persist(classicalComposition)
        entityManager.flush()

        val foundDisc = entityManager.find(Disc::class.java, disc.id)
        assertNotNull(foundDisc)
        assertTrue(foundDisc?.tracks?.contains(classicalComposition) ?: false)

        val foundClassicalComposition = entityManager.find(ClassicalComposition::class.java, classicalComposition.id)
        assertNotNull(foundClassicalComposition)
        assertEquals(disc, foundClassicalComposition?.disc)
    }
}
