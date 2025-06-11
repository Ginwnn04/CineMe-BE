package com.project.CineMe_BE.service;

import com.project.CineMe_BE.dto.request.ActorRequest;
import com.project.CineMe_BE.dto.response.ActorResponse;

public interface ActorService {
     ActorResponse createActor(ActorRequest actorRequest);

}
