package com.project.audiorecording.audiorecordingserver.service


interface CrudService<E, D, Id> {
    fun create(dto: D): D
    fun read(id: Id): D
    fun update(dto: D): D
    fun delete(id: Id)
    fun getAll(): List<D>
    fun requireOne(id: Id) : E
}