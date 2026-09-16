package com.dog.starwars_api.controller;


import com.dog.starwars_api.dto.WorldRequest;
import com.dog.starwars_api.dto.WorldResponse;
import com.dog.starwars_api.mapper.WorldMapper;
import com.dog.starwars_api.service.WorldService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/mundos")
public class WorldController {
    private final WorldMapper mapper;
    private final WorldService service;


    public WorldController(WorldMapper mapper, WorldService service) {
        this.mapper = mapper;
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<WorldResponse> createWorld(@Valid @RequestBody WorldRequest request) {
        WorldResponse response = mapper.toResponse(service.createWorld(request));
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }


}
