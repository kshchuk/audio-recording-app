package com.project.audiorecording.audiorecordingserver.controller

import com.project.audiorecording.audiorecordingserver.domain.dto.RockCompositionDto
import com.project.audiorecording.audiorecordingserver.service.RockCompositionService
import org.springframework.web.bind.annotation.RestController
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
class RockCompositionController(
    val rockCompositionService: RockCompositionService
) : IRestController<RockCompositionDto, UUID> {
        @GetMapping("/rock-compositions")
        override fun getAll(): ResponseEntity<List<RockCompositionDto>> {
            return ResponseEntity.ok(rockCompositionService.getAll())
        }
        @PostMapping("/rock-compositions")
        override fun create(@RequestBody entity: RockCompositionDto): ResponseEntity<RockCompositionDto> {
            return ResponseEntity.ok(rockCompositionService.create(entity))
        }

        @DeleteMapping("/rock-compositions/{id}")
        override fun delete(@PathVariable id: UUID): ResponseEntity<Unit> {
            rockCompositionService.delete(id)
            return ResponseEntity.ok().build()
        }

        @PutMapping("/rock-compositions/{id}")
        override fun update(@PathVariable id: UUID, @RequestBody entity: RockCompositionDto): ResponseEntity<RockCompositionDto> {
            return ResponseEntity.ok(rockCompositionService.update(entity))
        }

        @GetMapping("/rock-compositions/{id}")
        override fun getOne(@PathVariable id: UUID): ResponseEntity<RockCompositionDto> {
            return ResponseEntity.ok(rockCompositionService.read(id))
        }
}