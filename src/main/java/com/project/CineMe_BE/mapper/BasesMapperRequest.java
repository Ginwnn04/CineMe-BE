package com.project.CineMe_BE.mapper;

import org.mapstruct.BeanMapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

public interface BasesMapperRequest<D, E> {
    E toEntity(D dto);
    List<E> toListEntity(List<D> dtoList);
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void update(@MappingTarget E entity, D dto);


}
