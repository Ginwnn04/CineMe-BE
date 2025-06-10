package com.project.CineMe_BE.mapper;
import java.util.List;

public interface BaseMapperResponse<D, E> {
    D toDto(E entity);
    List<D> toListDto(List<E> entityList);

}
