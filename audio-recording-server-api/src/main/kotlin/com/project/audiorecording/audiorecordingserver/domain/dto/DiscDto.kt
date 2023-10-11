package com.project.audiorecording.audiorecordingserver.domain.dto

import java.io.Serializable
import java.time.Duration
import java.util.*

/**
 * DTO for {@link com.project.audiorecording.audiorecordingserver.domain.entity.Disc}
 */
data class DiscDto(val id: UUID? = null, val totalDuration: Duration? = null) : Serializable