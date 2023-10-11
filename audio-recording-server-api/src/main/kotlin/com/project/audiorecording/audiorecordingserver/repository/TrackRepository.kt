package com.project.audiorecording.audiorecordingserver.repository;

import com.project.audiorecording.audiorecordingserver.domain.entity.Track
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.repository.ListPagingAndSortingRepository
import java.util.*

interface TrackRepository : ListPagingAndSortingRepository<Track, UUID>, JpaRepository<Track, UUID> {
}