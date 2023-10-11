package com.project.audiorecording.audiorecordingserver.domain.dto

import java.io.Serializable
import java.time.Duration
import java.util.*

/**
 * DTO for {@link com.project.audiorecording.audiorecordingserver.domain.entity.RockComposition}
 */
data class RockCompositionDto(
    var style: String? = null
) : TrackDto()