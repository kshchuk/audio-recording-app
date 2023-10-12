package com.project.audiorecording.audiorecordingserver.controller

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

interface IRestController<T, ID> {

    @GetMapping
    fun getAll(): ResponseEntity<List<T>>

    @GetMapping("/{id}")
    fun getOne(@PathVariable id: ID): ResponseEntity<T>

    @PostMapping
    fun create(@RequestBody entity: T): ResponseEntity<T>

    @PutMapping("/{id}")
    fun update(@PathVariable id: ID, @RequestBody entity: T): ResponseEntity<T>

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: ID): ResponseEntity<Unit>
}
