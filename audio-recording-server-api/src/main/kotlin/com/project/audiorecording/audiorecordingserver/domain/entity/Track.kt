package com.project.audiorecording.audiorecordingserver.domain.entity

import jakarta.persistence.*
import org.hibernate.annotations.OnDelete
import org.hibernate.annotations.OnDeleteAction
import java.time.Duration
import java.util.*

@Entity
@Table(name = "tracks")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
abstract class Track {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "track_id", nullable = false)
    open var id: UUID? = null

    @Column(name = "title", nullable = false)
    open var title: String? = null

    @Column(name = "author", length = 100)
    open var author: String? = null

    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "disc_id")
    open var disc: Disc? = null

    @Column(name = "duration", nullable = false)
    open var duration: Duration? = null
}