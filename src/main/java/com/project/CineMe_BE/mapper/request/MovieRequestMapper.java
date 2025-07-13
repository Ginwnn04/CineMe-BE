package com.project.CineMe_BE.mapper.request;

import com.project.CineMe_BE.dto.request.MovieRequest;
import com.project.CineMe_BE.entity.*;
import com.project.CineMe_BE.mapper.BaseRequestMapper;
import org.mapstruct.*;

import java.util.UUID;

@Mapper(componentModel = "spring")
public interface MovieRequestMapper extends BaseRequestMapper<MovieRequest, MovieEntity> {
    @Mapping(target = "trailer", ignore = true)
    @Mapping(target = "image", ignore = true)
    @Mapping(target = "country", source = "countryId")
    @Mapping(target = "format", source = "formatId")
    @Mapping(target = "limitage", source = "limitageId")
    @Mapping(target = "language", source = "languageId")
    MovieEntity toEntity(MovieRequest dto);


    @Mapping(target = "trailer", ignore = true)
    @Mapping(target = "image", ignore = true)
    @Mapping(target = "country", source = "countryId")
    @Mapping(target = "format", source = "formatId")
    @Mapping(target = "limitage", source = "limitageId")
    @Mapping(target = "language", source = "languageId")
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)

    void update(@MappingTarget MovieEntity entity, MovieRequest dto);



    default CountryEntity mapCountry(UUID countryId) {
        if (countryId == null) {
            return null;
        }
        return CountryEntity.builder()
                .id(countryId)
                .build();
    }

    default LimitageEntity mapLimitage(UUID limitageId) {
        if (limitageId == null) {
            return null;
        }
        return LimitageEntity.builder()
                .id(limitageId)
                .build();
    }


    default FormatEntity mapFormat(UUID formatId) {
        if (formatId == null) {
            return null;
        }
        return FormatEntity.builder()
                .id(formatId)
                .build();
    }


    default LanguageEntity mapLanguage(UUID languageId) {
        if (languageId == null) {
            return null;
        }
        return LanguageEntity.builder()
                .id(languageId)
                .build();
    }


}