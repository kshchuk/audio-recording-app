package com.project.audiorecording.audiorecordingserver.service

import com.project.audiorecording.audiorecordingserver.domain.dto.TrackDto
import com.project.audiorecording.audiorecordingserver.domain.entity.Track
import java.util.*

interface ITrackService : CrudService<Track, TrackDto, UUID> {
    fun updateDisc(discId: UUID)
}