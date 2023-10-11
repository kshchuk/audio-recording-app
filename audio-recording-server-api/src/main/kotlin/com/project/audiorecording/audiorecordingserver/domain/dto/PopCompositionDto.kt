package com.project.audiorecording.audiorecordingserver.domain.dto

import java.io.Serializable
import java.time.Duration
import java.util.*

/**
 * DTO for {@link com.project.audiorecording.audiorecordingserver.domain.entity.PopComposition}
 */
data class PopCompositionDto(
    val genre: String? = null,
    val popularity: Int? = null
) : TrackDto()