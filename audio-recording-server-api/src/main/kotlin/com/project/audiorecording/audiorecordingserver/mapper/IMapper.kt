package com.project.audiorecording.audiorecordingserver.mapper

import org.mapstruct.BeanMapping
import org.mapstruct.MapperConfig
import org.mapstruct.MappingTarget
import org.mapstruct.NullValuePropertyMappingStrategy

@MapperConfig(componentModel = "spring")
interface IMapper<E, D, Id> {
    fun toEntity(dto: D, foundEntity: E): E
    fun toDto(entity: E): D
    fun ToDtoList(entities: List<E>?): List<D>?
    fun toDtoMap(entities: List<E>?): Map<Id, D>?

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    fun partialUpdate(discDto: D, @MappingTarget disc: E): E
}