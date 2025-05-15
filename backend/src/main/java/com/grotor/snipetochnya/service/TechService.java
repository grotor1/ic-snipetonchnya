package com.grotor.snipetochnya.service;

import com.grotor.snipetochnya.dto.request.TechRequest;
import com.grotor.snipetochnya.dto.response.TechResponse;
import com.grotor.snipetochnya.model.Tech;
import org.apache.coyote.Response;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface TechService {
    List<TechResponse> getAllTechs();

    TechResponse getTechById(UUID id);

    TechResponse createTech(TechRequest tech);

    TechResponse updateTech(TechRequest tech, UUID id);

    void deleteTech(UUID id);
}