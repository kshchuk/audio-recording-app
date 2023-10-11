package com.project.audiorecording.audiorecordingserver.repository;

import com.project.audiorecording.audiorecordingserver.domain.entity.Disc
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface DiscRepository : JpaRepository<Disc, UUID> {
}