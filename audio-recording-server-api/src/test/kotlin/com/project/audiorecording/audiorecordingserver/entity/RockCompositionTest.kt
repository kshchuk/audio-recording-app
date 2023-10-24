package com.project.audiorecording.audiorecordingserver.domain.entity

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager
import java.time.Duration

@DataJpaTest
class RockCompositionEntityTests(@Autowired val entityManager: TestEntityManager) {

    @Test
    fun `When persisting a rock composition, it should be retrievable`() {
        val rockComposition = RockComposition(
            style = "Rock"
        )
        rockComposition.title = "Test Track"
        rockComposition.author = "Test Author"
        rockComposition.duration = Duration.ofMinutes(3)

        entityManager.persist(rockComposition)
        entityManager.flush()

        val foundRockComposition = entityManager.find(RockComposition::class.java, rockComposition.id)
        assertNotNull(foundRockComposition)
        assertEquals(rockComposition.title, foundRockComposition?.title)
        assertEquals(rockComposition.author, foundRockComposition?.author)
        assertEquals(rockComposition.duration, foundRockComposition?.duration)
        assertEquals(rockComposition.style, foundRockComposition?.style)
    }

    @Test
    fun `When persisting a rock composition with a disc, the relationship should be retrievable`() {
        val disc = Disc(
            name = "Test Disc",
            trackNumber = 10,
            totalDuration = Duration.ofHours(1)
        )
        val rockComposition = RockComposition(
            style = "Rock",
        )
        rockComposition.title = "Test Track"
        rockComposition.author = "Test Author"
        rockComposition.duration = Duration.ofMinutes(3)
        rockComposition.disc = disc

        entityManager.persist(disc)
        entityManager.persist(rockComposition)
        entityManager.flush()

        val foundDisc = entityManager.find(Disc::class.java, disc.id)
        assertNotNull(foundDisc)
        assertTrue(foundDisc?.tracks?.contains(rockComposition) ?: false)

        val foundRockComposition = entityManager.find(RockComposition::class.java, rockComposition.id)
        assertNotNull(foundRockComposition)
        assertEquals(disc, foundRockComposition?.disc)
    }
}
