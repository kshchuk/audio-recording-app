package com.project.audiorecording.audiorecordingserver.controller

import com.project.audiorecording.audiorecordingserver.domain.dto.DiscDto
import com.project.audiorecording.audiorecordingserver.domain.dto.TrackDto
import com.project.audiorecording.audiorecordingserver.service.DiscService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.time.Duration
import java.util.*

@RestController
class DiscController(
    private val discService: DiscService,
) : IRestController<DiscDto, UUID> {
    @GetMapping("/discs")
    override fun getAll(): ResponseEntity<List<DiscDto>> {
        return ResponseEntity.ok(discService.getAll())
    }

    @GetMapping("/discs/{id}/tracks")
    fun getAllTracks(@PathVariable  id: UUID) : ResponseEntity<List<TrackDto>> {
        return ResponseEntity.ok(discService.getAllTracks(id))
    }

    @PostMapping("/discs")
    override fun create(@RequestBody entity: DiscDto): ResponseEntity<DiscDto> {
        return ResponseEntity.ok(discService.create(entity))
    }

    @DeleteMapping("/discs/{id}")
    override fun delete(@PathVariable id: UUID): ResponseEntity<Unit> {
        discService.delete(id)
        return ResponseEntity.ok().build()
    }

    @PutMapping("/discs/{id}")
    override fun update(@PathVariable id: UUID, @RequestBody entity: DiscDto): ResponseEntity<DiscDto> {
        return ResponseEntity.ok(discService.update(entity))
    }

    @GetMapping("/discs/{id}")
    override fun getOne(@PathVariable id: UUID): ResponseEntity<DiscDto> {
        return ResponseEntity.ok(discService.read(id))
    }

    @GetMapping("/discs/{id}/duration")
    fun calculateDuration(@PathVariable id: UUID): Duration {
        return discService.calculateTotalDuration(id)
    }

    @GetMapping("/discs/{id}/sort")
    fun getSortedByStyle(@PathVariable id: UUID): List<TrackDto> {
        return discService.sortSongsByStyle(id)
    }

    @GetMapping("/discs/{id}/filter")
    fun findSongsByLenght(@PathVariable id: UUID,
                          @RequestParam min: Duration,
                          @RequestParam max: Duration): List<TrackDto> {
        return discService.findSongsByLenght(id, min, max)
    }
}