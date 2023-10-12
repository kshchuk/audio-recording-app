package com.project.audiorecording.audiorecordingserver.domain.dto

import java.io.Serializable
import java.time.Duration
import java.util.*
import javax.sound.midi.Track

/**
 * DTO for {@link com.project.audiorecording.audiorecordingserver.domain.entity.RockComposition}
 */
data class RockCompositionDto(
    var style: String? = null
) : TrackDto() {
    constructor(track: TrackDto, style: String?) : this() {
        this.id = track.id
        this.title = track.title
        this.author = track.author
        this.disc = track.disc
        this.duration = track.duration
        this.style = style
    }
}