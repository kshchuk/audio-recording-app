package com.project.audiorecording.audiorecordingserver.repository;

import com.project.audiorecording.audiorecordingserver.domain.entity.PopComposition
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface PopCompositionRepository : JpaRepository<PopComposition, UUID> {
}