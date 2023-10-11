package com.project.audiorecording.audiorecordingserver.domain.dto

import java.io.Serializable
import java.time.Duration
import java.util.*

/**
 * DTO for {@link com.project.audiorecording.audiorecordingserver.domain.entity.Track}
 */
abstract class TrackDto(
    val id: UUID? = null,
    val title: String? = null,
    val author: String? = null,
    val disc: DiscDto? = null,
    val duration: Duration? = null
) : Serializable