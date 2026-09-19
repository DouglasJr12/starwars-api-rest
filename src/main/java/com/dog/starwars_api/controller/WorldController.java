package com.dog.starwars_api.controller;


import com.dog.starwars_api.dto.WorldRequest;
import com.dog.starwars_api.dto.WorldResponse;
import com.dog.starwars_api.mapper.WorldMapper;
import com.dog.starwars_api.service.WorldService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("/local")
    public ResponseEntity<List<WorldResponse>> findAllWorldLocal(){
            List<WorldResponse> responseList = mapper.toWorldsResponseList(service.findAllWorldsLocal()) ;
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseList);
    }

    @PutMapping("/{id}")
    public ResponseEntity<WorldResponse> updateWorld(@PathVariable Long id,@RequestBody WorldRequest request){
        WorldResponse response = mapper.toResponse(service.updateWorld(id,request));
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteWorld(@PathVariable Long id){
            service.deleteWorld(id);
            return ResponseEntity.noContent().build();
    }



}
