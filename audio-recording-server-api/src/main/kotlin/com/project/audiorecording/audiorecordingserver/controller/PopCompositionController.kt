package com.project.audiorecording.audiorecordingserver.controller

import com.project.audiorecording.audiorecordingserver.domain.dto.PopCompositionDto
import com.project.audiorecording.audiorecordingserver.service.PopCompositionService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
class PopCompositionController(
    val popCompositionService: PopCompositionService
) : IRestController<PopCompositionDto, UUID> {
        @GetMapping("/pop-compositions")
        override fun getAll(): ResponseEntity<List<PopCompositionDto>> {
            return ResponseEntity.ok(popCompositionService.getAll())
        }
        @PostMapping("/pop-compositions")
        override fun create(@RequestBody entity: PopCompositionDto): ResponseEntity<PopCompositionDto> {
            return ResponseEntity.ok(popCompositionService.create(entity))
        }

        @DeleteMapping("/pop-compositions/{id}")
        override fun delete(@PathVariable id: UUID): ResponseEntity<Unit> {
            popCompositionService.delete(id)
            return ResponseEntity.ok().build()
        }

        @PutMapping("/pop-compositions/{id}")
        override fun update(@PathVariable id: UUID, @RequestBody entity: PopCompositionDto): ResponseEntity<PopCompositionDto> {
            return ResponseEntity.ok(popCompositionService.update(entity))
        }

        @GetMapping("/pop-compositions/{id}")
        override fun getOne(@PathVariable id: UUID): ResponseEntity<PopCompositionDto> {
            return ResponseEntity.ok(popCompositionService.read(id))
        }
}