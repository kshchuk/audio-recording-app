package com.project.audiorecording.audiorecordingserver.repository;

import com.project.audiorecording.audiorecordingserver.domain.entity.RockComposition
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface RockCompositionRepository : JpaRepository<RockComposition, UUID> {
}