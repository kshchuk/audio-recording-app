package com.project.audiorecording.audiorecordingserver.repository;

import com.project.audiorecording.audiorecordingserver.domain.entity.RockComposition
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface RockCompositionRepository : JpaRepository<RockComposition, UUID> {
}