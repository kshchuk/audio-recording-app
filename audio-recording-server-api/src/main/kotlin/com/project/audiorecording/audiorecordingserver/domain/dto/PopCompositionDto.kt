package com.project.audiorecording.audiorecordingserver.domain.dto

import java.io.Serializable
import java.time.Duration
import java.util.*

/**
 * DTO for {@link com.project.audiorecording.audiorecordingserver.domain.entity.PopComposition}
 */
data class PopCompositionDto(
    var genre: String? = null,
    var popularity: Int? = null
) : TrackDto() {
    constructor(track: TrackDto, genre: String?, popularity: Int?) : this() {
        this.id = track.id
        this.title = track.title
        this.author = track.author
        this.disc = track.disc
        this.duration = track.duration
        this.genre = genre
        this.popularity = popularity
    }
}