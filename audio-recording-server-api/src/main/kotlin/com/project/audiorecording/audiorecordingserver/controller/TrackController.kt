package com.project.audiorecording.audiorecordingserver.controller

import com.project.audiorecording.audiorecordingserver.domain.dto.TrackDto
import com.project.audiorecording.audiorecordingserver.service.TrackService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
class TrackController(
    val trackService: TrackService
) : IRestController<TrackDto, UUID> {

    @GetMapping("/tracks")
    override fun getAll(): ResponseEntity<List<TrackDto>> {
        return ResponseEntity.ok(trackService.getAll())
    }

    @PostMapping("/tracks")
    override fun create(@RequestBody entity: TrackDto): ResponseEntity<TrackDto> {
        return ResponseEntity.ok(trackService.create(entity))
    }

    @DeleteMapping("/tracks/{id}")
    override fun delete(@PathVariable id: UUID): ResponseEntity<Unit> {
        trackService.delete(id)
        return ResponseEntity.ok().build()
    }

    @PutMapping("/tracks/{id}")
    override fun update(@PathVariable id: UUID, @RequestBody entity: TrackDto): ResponseEntity<TrackDto> {
        return ResponseEntity.ok(trackService.update(entity))
    }

    @GetMapping("/tracks/{id}")
    override fun getOne(@PathVariable id: UUID): ResponseEntity<TrackDto> {
        return ResponseEntity.ok(trackService.read(id))
    }
}