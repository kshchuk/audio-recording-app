package com.project.audiorecording.audiorecordingserver.repository;

import com.project.audiorecording.audiorecordingserver.domain.entity.ClassicalComposition
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface ClassicalCompositionRepository : JpaRepository<ClassicalComposition, UUID> {
}