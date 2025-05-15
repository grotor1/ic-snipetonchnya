package com.grotor.snipetochnya.controller;

import com.grotor.snipetochnya.dto.request.TagRequest;
import com.grotor.snipetochnya.dto.request.TechRequest;
import com.grotor.snipetochnya.dto.response.TagResponse;
import com.grotor.snipetochnya.dto.response.TechResponse;
import com.grotor.snipetochnya.service.TagService;
import com.grotor.snipetochnya.service.TechService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/techs")
@RequiredArgsConstructor
public class TechController {
    private final TechService techService;

    @GetMapping
    public List<TechResponse> getAllTechs() {
        return techService.getAllTechs();
    }

    @GetMapping("/{id}")
    public TechResponse getTechById(@PathVariable UUID id) {
        return techService.getTechById(id);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping
    public TechResponse addTech(@RequestBody TechRequest techRequest) {
        return techService.createTech(techRequest);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PatchMapping("/{id}")
    public TechResponse updateTech(@RequestBody TechRequest techRequest, @PathVariable UUID id) {
        return techService.updateTech(techRequest, id);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTech(@PathVariable UUID id) {
        techService.deleteTech(id);
    }
}
