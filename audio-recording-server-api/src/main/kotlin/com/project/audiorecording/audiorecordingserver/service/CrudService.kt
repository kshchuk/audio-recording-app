package com.project.audiorecording.audiorecordingserver.service


interface CrudService<E, D, Id> {
    fun create(dto: D): E
    fun read(id: Id): D
    fun update(dto: D): E
    fun delete(id: Id)
    fun getAll(): List<D>
    fun requireOne(id: Id) : E
}