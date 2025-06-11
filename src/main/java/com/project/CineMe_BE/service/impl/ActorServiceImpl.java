package com.project.CineMe_BE.service.impl;

import com.project.CineMe_BE.dto.request.ActorRequest;
import com.project.CineMe_BE.dto.response.ActorResponse;
import com.project.CineMe_BE.entity.ActorEntity;
import com.project.CineMe_BE.mapper.request.ActorRequestMapper;
import com.project.CineMe_BE.mapper.response.ActorResponseMapper;
import com.project.CineMe_BE.repository.ActorRepository;
import com.project.CineMe_BE.service.ActorService;
import com.project.CineMe_BE.service.MinioService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class ActorServiceImpl implements ActorService {
    private final MinioService minioService;
    private final ActorRepository actorRepository;
    private final ActorRequestMapper actorRequestMapper;
    private final ActorResponseMapper actorResponseMapper;

    @Override
    public ActorResponse createActor(ActorRequest actorRequest) {
        ActorEntity actor = actorRequestMapper.toEntity(actorRequest);
        if (actorRequest.getImg() != null) {
            String imgUrl = minioService.upload(actorRequest.getImg());
            imgUrl = imgUrl.substring(imgUrl.indexOf("resource"), imgUrl.indexOf("?"));
            actor.setImg(imgUrl);
        }
        actorRepository.save(actor);
        ActorResponse r = actorResponseMapper.toDto(actor);
        return r;
    }
}
