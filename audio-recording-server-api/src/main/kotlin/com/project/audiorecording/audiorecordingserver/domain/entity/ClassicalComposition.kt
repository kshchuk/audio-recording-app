package com.project.audiorecording.audiorecordingserver.domain.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Table

@Entity
@Table(name = "classical_compositions")
data class ClassicalComposition(
    @Column(name = "epoch", nullable = false, length = 50)
    var epoch: String? = null
) : Track()