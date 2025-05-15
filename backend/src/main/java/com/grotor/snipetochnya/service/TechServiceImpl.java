package com.grotor.snipetochnya.service;

import com.grotor.snipetochnya.dto.request.TechRequest;
import com.grotor.snipetochnya.dto.response.TechResponse;
import com.grotor.snipetochnya.mapper.TechMapper;
import com.grotor.snipetochnya.model.Tech;
import com.grotor.snipetochnya.repository.TechJpaRepository;
import com.grotor.snipetochnya.security.exception.TechNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class TechServiceImpl implements TechService {
    private final TechJpaRepository techJpaRepository;
    private final TechMapper techMapper;

    @Override
    public List<TechResponse> getAllTechs() {
        return techJpaRepository.findAll().stream().map(techMapper::toResponse).toList();
    }

    @Override
    public TechResponse getTechById(UUID id) {
        return techJpaRepository.findById(id).map(techMapper::toResponse).orElseThrow(() -> new TechNotFoundException(id));
    }

    @Override
    public TechResponse createTech(TechRequest techRequest) {
        Tech tech = Tech.builder()
                .label(techRequest.label())
                .createdAt(LocalDateTime.now())
                .build();
        return techMapper.toResponse(techJpaRepository.save(tech));
    }

    @Override
    public TechResponse updateTech(TechRequest techRequest, UUID id) {
        Tech tech = techJpaRepository.findById(id).orElseThrow(() -> new TechNotFoundException(id));
        tech.setLabel(techRequest.label() == null ? tech.getLabel() : techRequest.label());
        return techMapper.toResponse(techJpaRepository.save(tech));
    }

    @Override
    public void deleteTech(UUID id) {
        techJpaRepository.deleteById(id);
    }
}
