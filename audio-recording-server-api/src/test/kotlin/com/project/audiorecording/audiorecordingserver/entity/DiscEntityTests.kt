package com.project.audiorecording.audiorecordingserver.domain.entity

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager
import java.time.Duration

@DataJpaTest
class DiscEntityTests(@Autowired val entityManager: TestEntityManager) {

    @Test
    fun `When persisting a disc, it should be retrievable`() {
        val disc = Disc(
            name = "Test Disc",
            trackNumber = 10,
            totalDuration = Duration.ofHours(1)
        )
        entityManager.persist(disc)
        entityManager.flush()

        val foundDisc = entityManager.find(Disc::class.java, disc.id)
        assertNotNull(foundDisc)
        assertEquals(disc.name, foundDisc?.name)
        assertEquals(disc.trackNumber, foundDisc?.trackNumber)
        assertEquals(disc.totalDuration, foundDisc?.totalDuration)
    }

    @Test
    fun `When persisting a disc with tracks, the relationship should be retrievable`() {
        val track = Track(
            title = "Test Track",
            author = "Test Author",
            duration = Duration.ofMinutes(3)
        )
        val disc = Disc(
            name = "Test Disc",
            trackNumber = 10,
            totalDuration = Duration.ofHours(1),
            tracks = mutableListOf(track)
        )
        track.disc = disc
        entityManager.persist(track)
        entityManager.persist(disc)
        entityManager.flush()

        val foundTrack = entityManager.find(Track::class.java, track.id)
        assertNotNull(foundTrack)
        assertEquals(disc, foundTrack?.disc)

        val foundDisc = entityManager.find(Disc::class.java, disc.id)
        assertNotNull(foundDisc)
        assertTrue(foundDisc?.tracks?.contains(track) ?: false)
    }
}
