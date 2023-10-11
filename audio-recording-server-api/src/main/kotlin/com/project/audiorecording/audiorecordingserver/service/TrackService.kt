package com.project.audiorecording.audiorecordingserver.service

import com.project.audiorecording.audiorecordingserver.domain.dto.DiscDto
import com.project.audiorecording.audiorecordingserver.domain.dto.TrackDto
import com.project.audiorecording.audiorecordingserver.domain.entity.Disc
import com.project.audiorecording.audiorecordingserver.domain.entity.Track
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class TrackService(
    private val discService: CrudService<Disc, DiscDto, UUID>
)
    : CrudService<Track, TrackDto, UUID>
{
    override fun create(dto: TrackDto): Track {
        TODO()
    }

    override fun read(id: UUID): Track {
        TODO()
    }

    override fun update(dto: TrackDto): Track {
        TODO()
    }

    override fun delete(id: UUID) {
        TODO()
    }

    override fun getAll(): List<Track> {
        TODO()
    }
}