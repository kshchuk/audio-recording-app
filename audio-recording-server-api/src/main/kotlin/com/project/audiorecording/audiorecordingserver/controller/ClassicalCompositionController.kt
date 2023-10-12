package com.project.audiorecording.audiorecordingserver.controller

import com.project.audiorecording.audiorecordingserver.domain.dto.ClassicalCompositionDto
import com.project.audiorecording.audiorecordingserver.service.ClassicalCompositionService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.UUID

@RestController
class ClassicalCompositionController(
    val classicalCompositionService: ClassicalCompositionService
) : IRestController<ClassicalCompositionDto, UUID> {

        @GetMapping("/classical-compositions")
        override fun getAll(): ResponseEntity<List<ClassicalCompositionDto>> {
            return ResponseEntity.ok(classicalCompositionService.getAll())
        }

        @PostMapping("/classical-compositions")
        override fun create(@RequestBody entity: ClassicalCompositionDto): ResponseEntity<ClassicalCompositionDto> {
            return ResponseEntity.ok(classicalCompositionService.create(entity))
        }

        @DeleteMapping("/classical-compositions/{id}")
        override fun delete(@PathVariable id: UUID): ResponseEntity<Unit> {
            classicalCompositionService.delete(id)
            return ResponseEntity.ok().build()
        }

        @PutMapping("/classical-compositions/{id}")
        override fun update(@PathVariable id: UUID, @RequestBody entity: ClassicalCompositionDto): ResponseEntity<ClassicalCompositionDto> {
            return ResponseEntity.ok(classicalCompositionService.update(entity))
        }

        @GetMapping("/classical-compositions/{id}")
        override fun getOne(@PathVariable id: UUID): ResponseEntity<ClassicalCompositionDto> {
            return ResponseEntity.ok(classicalCompositionService.read(id))
        }
}