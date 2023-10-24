package com.project.audiorecording.audiorecordingserver.domain.entity

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager
import java.time.Duration

@DataJpaTest
class TrackEntityTests(@Autowired val entityManager: TestEntityManager) {

    @Test
    fun `When persisting a track, it should be retrievable`() {
        val track = Track(
            title = "Test Track",
            author = "Test Author",
            duration = Duration.ofMinutes(3)
        )
        entityManager.persist(track)
        entityManager.flush()

        val foundTrack = entityManager.find(Track::class.java, track.id)
        assertNotNull(foundTrack)
        assertEquals(track.title, foundTrack?.title)
        assertEquals(track.author, foundTrack?.author)
        assertEquals(track.duration, foundTrack?.duration)
    }

    @Test
    fun `When persisting a track with a disc, the relationship should be retrievable`() {
        val disc = Disc(
            name = "Test Disc",
            trackNumber = 10,
            totalDuration = Duration.ofHours(1)
        )
        val track = Track(
            title = "Test Track",
            author = "Test Author",
            duration = Duration.ofMinutes(3),
            disc = disc
        )
        entityManager.persist(disc)
        entityManager.persist(track)
        entityManager.flush()

        val foundDisc = entityManager.find(Disc::class.java, disc.id)
        assertNotNull(foundDisc)
        assertTrue(foundDisc?.tracks?.contains(track) ?: false)

        val foundTrack = entityManager.find(Track::class.java, track.id)
        assertNotNull(foundTrack)
        assertEquals(disc, foundTrack?.disc)
    }
}
