package com.project.audiorecording.audiorecordingserver.service

import com.project.audiorecording.audiorecordingserver.domain.dto.DiscDto
import com.project.audiorecording.audiorecordingserver.domain.dto.TrackDto
import com.project.audiorecording.audiorecordingserver.domain.entity.Disc
import java.time.Duration
import java.util.*

interface IDiscService : CrudService<Disc, DiscDto, UUID> {
    fun getAllTracks(discId: UUID) : List<TrackDto>
    fun calculateTotalDuration(discId: UUID) : Duration
    fun updateDisc(discId: UUID) : DiscDto
    fun findSongsByLength(disc: UUID, min: Duration, max: Duration) : List<TrackDto>
    fun sortSongsByStyle(disc: UUID) : List<TrackDto>
}