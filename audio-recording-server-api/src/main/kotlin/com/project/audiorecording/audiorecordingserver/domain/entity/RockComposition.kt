package com.project.audiorecording.audiorecordingserver.domain.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Table

@Entity
@Table(name = "rock_compositions")
data class RockComposition(
    @Column(name = "style", nullable = false, length = 100)
    var style: String? = null
) : Track()