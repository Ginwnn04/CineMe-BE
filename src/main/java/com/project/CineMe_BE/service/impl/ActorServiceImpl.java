package com.project.CineMe_BE.service.impl;

import com.project.CineMe_BE.constant.MessageKey;
import com.project.CineMe_BE.dto.request.ActorRequest;
import com.project.CineMe_BE.dto.response.ActorResponse;
import com.project.CineMe_BE.entity.ActorEntity;
import com.project.CineMe_BE.exception.DataNotFoundException;
import com.project.CineMe_BE.mapper.request.ActorRequestMapper;
import com.project.CineMe_BE.mapper.response.ActorResponseMapper;
import com.project.CineMe_BE.repository.ActorRepository;
import com.project.CineMe_BE.service.ActorService;
import com.project.CineMe_BE.service.MinioService;
import com.project.CineMe_BE.utils.LocalizationUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;


@Service
@RequiredArgsConstructor
public class ActorServiceImpl implements ActorService {
    private final MinioService minioService;
    private final ActorRepository actorRepository;
    private final ActorRequestMapper actorRequestMapper;
    private final ActorResponseMapper actorResponseMapper;
    private final LocalizationUtils localizationUtils;


    @Override
    public ActorResponse getActorById(UUID id) {
        ActorEntity actor = actorRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException(
                        localizationUtils.getLocalizedMessage(MessageKey.ACTOR_NOT_FOUND)
                ));

        return actorResponseMapper.toDto(actor);
    }

    @Override
    public ActorResponse createActor(ActorRequest actorRequest) {
        ActorEntity actor = actorRequestMapper.toEntity(actorRequest);
        if (actorRequest.getImg() != null) {
            String imgUrl = minioService.upload(actorRequest.getImg());
            imgUrl = imgUrl.substring(imgUrl.indexOf("resource"), imgUrl.indexOf("?"));
            actor.setImg(imgUrl);
        }
        return actorResponseMapper.toDto(actorRepository.save(actor));
    }

    @Override
    public boolean deleteActor(UUID id) {
        ActorEntity actor = actorRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException(
                        localizationUtils.getLocalizedMessage(MessageKey.ACTOR_NOT_FOUND)
                ));
        actorRepository.deleteById(id);
        return true;
    }

    @Override
    public ActorResponse updateActor(UUID id, ActorRequest actorRequest) {
        ActorEntity actor = actorRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException(
                        localizationUtils.getLocalizedMessage(MessageKey.ACTOR_NOT_FOUND)
                ));
        actorRequestMapper.update(actor, actorRequest);
        if (actorRequest.getImg() != null) {
            String imgUrl = minioService.upload(actorRequest.getImg());
            imgUrl = imgUrl.substring(imgUrl.indexOf("resource"), imgUrl.indexOf("?"));
            actor.setImg(imgUrl);
        }
        return actorResponseMapper.toDto(actorRepository.save(actor));
    }
}
