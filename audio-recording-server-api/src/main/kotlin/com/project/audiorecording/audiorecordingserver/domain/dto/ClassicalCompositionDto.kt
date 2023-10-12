package com.project.audiorecording.audiorecordingserver.domain.dto

import java.io.Serializable
import java.time.Duration
import java.util.*

/**
 * DTO for {@link com.project.audiorecording.audiorecordingserver.domain.entity.ClassicalComposition}
 */
data class ClassicalCompositionDto(
    var epoch: String? = null
) : TrackDto() {
    constructor(track: TrackDto, epoch: String?) : this() {
        this.id = track.id
        this.title = track.title
        this.author = track.author
        this.disc = track.disc
        this.duration = track.duration
        this.epoch = epoch
    }
}