package com.project.CineMe_BE.mapper.response;

import com.project.CineMe_BE.dto.response.ActorResponse;
import com.project.CineMe_BE.dto.response.MovieResponse;
import com.project.CineMe_BE.entity.ActorEntity;
import com.project.CineMe_BE.mapper.BaseResponseMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ActorResponseMapper extends BaseResponseMapper<ActorResponse, ActorEntity> {

    default ActorResponse toDto(ActorEntity entity) {
        if (entity == null) {
            return null;
        }
        ActorResponse actorResponse = ActorResponse.builder()
                .id(entity.getId())
                .name(entity.getName())
                .listMovie(entity.getListMovie() != null ? entity.getListMovie().stream()
                        .map(movie -> {
                            return MovieResponse.builder()
                                    .id(movie.getId())
                                    .nameEn(movie.getNameEn())
                                    .nameVn(movie.getNameVn())
                                    .image(movie.getImage())
                                    .build();
                        })
                        .toList() : null)
                .build();
        return actorResponse;
    }

}
