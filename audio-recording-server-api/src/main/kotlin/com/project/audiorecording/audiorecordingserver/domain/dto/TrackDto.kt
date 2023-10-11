package com.project.audiorecording.audiorecordingserver.domain.dto

import java.io.Serializable
import java.time.Duration
import java.util.*

/**
 * DTO for {@link com.project.audiorecording.audiorecordingserver.domain.entity.Track}
 */
abstract class TrackDto(
    var id: UUID? = null,
    var title: String? = null,
    var author: String? = null,
    var disc: DiscDto? = null,
    var duration: Duration? = null
) : Serializable