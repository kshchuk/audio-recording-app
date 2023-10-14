package com.project.audiorecording.audiorecordingserver.domain.dto

import java.io.Serializable
import java.time.Duration
import java.util.*

/**
 * DTO for {@link com.project.audiorecording.audiorecordingserver.domain.entity.Disc}
 */
data class DiscDto(
    var id: UUID? = null,
    var name: String? = null,
    var trackNumber: Int? = null,
    var totalDuration: Duration? = null
) : Serializable