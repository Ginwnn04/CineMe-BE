package com.project.CineMe_BE.mapper.response;

import com.project.CineMe_BE.dto.response.ActorResponse;
import com.project.CineMe_BE.dto.response.MovieResponse;
import com.project.CineMe_BE.entity.ActorEntity;
import com.project.CineMe_BE.entity.MovieEntity;
import com.project.CineMe_BE.mapper.BaseResponseMapper;
import com.project.CineMe_BE.utils.DomainUtil;
import com.project.CineMe_BE.utils.StringUtil;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MovieResponseMapper extends BaseResponseMapper<MovieResponse, MovieEntity> {

    @Mapping(target = "limitageNameVn", source = "limitage.nameVn")
    @Mapping(target = "limitageNameEn", source = "limitage.nameEn")
    @Mapping(target = "languageNameVn", source = "language.nameVn")
    @Mapping(target = "languageNameEn", source = "language.nameEn")
    @Mapping(target = "trailer", source = "trailer", qualifiedByName = "mapTrailer")
    @Mapping(target = "image", source = "image", qualifiedByName = "mapImage")
    @Mapping(target = "listActor", source = "listActor", qualifiedByName = "mapActor")
    MovieResponse toDto(MovieEntity entity);



    @Named("mapActor")
    default List<ActorResponse> mapActor(List<ActorEntity> listActor) {
        if (listActor == null) {
            return null;
        }
        return listActor.stream()
                .map(actor -> ActorResponse.builder()
                        .id(actor.getId())
                        .name(actor.getName())
                        .img(actor.getImg())
                        .build())
                .toList();
    }

    @Named("mapTrailer")
    default String mapTrailer(String trailer) {
        return StringUtil.mapTrailer(trailer);
    }

    @Named("mapImage")
    default String mapImage(String image) {
        return StringUtil.mapImg(image);
    }
}
