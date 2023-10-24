package com.project.audiorecording.audiorecordingserver.domain.entity

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager
import java.time.Duration
import java.util.*

@DataJpaTest
class PopCompositionEntityTests(@Autowired val entityManager: TestEntityManager) {

    @Test
    fun `When persisting a pop composition, it should be retrievable`() {
        val popComposition = PopComposition(
            genre = "Pop",
            popularity = 100
        )
        popComposition.title = "Test Track"
        popComposition.author = "Test Author"
        popComposition.duration = Duration.ofMinutes(3)

        entityManager.persist(popComposition)
        entityManager.flush()

        val foundPopComposition = entityManager.find(PopComposition::class.java, popComposition.id)
        assertNotNull(foundPopComposition)
        assertEquals(popComposition.title, foundPopComposition?.title)
        assertEquals(popComposition.author, foundPopComposition?.author)
        assertEquals(popComposition.duration, foundPopComposition?.duration)
        assertEquals(popComposition.genre, foundPopComposition?.genre)
        assertEquals(popComposition.popularity, foundPopComposition?.popularity)
    }

    @Test
    fun `When persisting a pop composition with a disc, the relationship should be retrievable`() {
        val disc = Disc(
            name = "Test Disc",
            trackNumber = 10,
            totalDuration = Duration.ofHours(1)
        )
        val popComposition = PopComposition(
            genre = "Pop",
            popularity = 100,
        )
        popComposition.title = "Test Track"
        popComposition.author = "Test Author"
        popComposition.duration = Duration.ofMinutes(3)
        popComposition.disc = disc

        entityManager.persist(disc)
        entityManager.persist(popComposition)
        entityManager.flush()

        val foundDisc = entityManager.find(Disc::class.java, disc.id)
        assertNotNull(foundDisc)
        assertTrue(foundDisc?.tracks?.contains(popComposition) ?: false)

        val foundPopComposition = entityManager.find(PopComposition::class.java, popComposition.id)
        assertNotNull(foundPopComposition)
        assertEquals(disc, foundPopComposition?.disc)
    }
}
