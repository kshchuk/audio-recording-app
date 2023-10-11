package com.project.audiorecording.audiorecordingserver.domain.entity

import jakarta.persistence.*
import java.time.Duration
import java.util.*

@Entity
@Table(name = "discs")
data class Disc(
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "\"disс_id\"", nullable = false)
    var id: UUID? = null,

    @Column(name = "total_duration")
    var totalDuration: Duration? = null
)