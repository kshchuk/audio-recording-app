package com.project.audiorecording.audiorecordingserver.domain.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Table

@Entity
@Table(name = "pop_compositions")
data class PopComposition(
    @Column(name = "genre", nullable = false, length = 100)
    var genre: String? = null,

    @Column(name = "popularity")
    var popularity: Int? = null
) : Track()