package com.dog.starwars_api.controller;


import com.dog.starwars_api.dto.SwapInfoDto;
import com.dog.starwars_api.dto.WorldRequest;
import com.dog.starwars_api.dto.WorldResponse;
import com.dog.starwars_api.mapper.WorldMapper;
import com.dog.starwars_api.service.SwapInfoApi;
import com.dog.starwars_api.service.WorldService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;





@RestController
@RequestMapping("/api/mundos")
@Tag(name = "Mundos",
        description = "Operações para gerenciamento de mundos")
public class WorldController {
    private final WorldMapper mapper;
    private final WorldService service;
    private final SwapInfoApi swapInfoApi;


    public WorldController(WorldMapper mapper, WorldService service, SwapInfoApi swapInfoApi) {
        this.mapper = mapper;
        this.service = service;
        this.swapInfoApi = swapInfoApi;
    }

    @Operation(
            summary = "Cadastrar mundo",
            description = "Cadastra um novo mundo no banco de dados local."
    )
    @PostMapping
    public ResponseEntity<WorldResponse> createWorld(@Valid @RequestBody WorldRequest request) {
        WorldResponse response = mapper.toResponse(service.createWorld(request));
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @Operation(
            summary = "Consultar mundos locais",
            description = "Retorna todos os mundos cadastrados localmente"
    )
    @GetMapping("/local")
    public ResponseEntity<List<WorldResponse>> findAllWorldLocal(){
            List<WorldResponse> responseList = mapper.toWorldsResponseList(service.findAllWorldsLocal()) ;
        return ResponseEntity.ok(responseList);
    }

    @Operation(
            summary = "Consultar mundo na SWAPI",
            description = "Consulta um mundo na API externa SWAPI utilizando seu ID."
    )
    @GetMapping("/externo/{id}")
    public ResponseEntity<SwapInfoDto> getSwapInfoWorlds(@PathVariable Long id){
        SwapInfoDto swapInfoDto = swapInfoApi.getWorldsApi(id);
         return ResponseEntity.ok(swapInfoDto);
    }

    @Operation(
            summary = "Atualizar um mundo",
            description = "Atualiza os dados de um mundo existente."
    )
    @PutMapping("/{id}")
    public ResponseEntity<WorldResponse> updateWorld(@PathVariable Long id,@RequestBody WorldRequest request){
        WorldResponse response = mapper.toResponse(service.updateWorld(id,request));
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @Operation(
            summary = "Excluir mundo",
            description = "Exclui um mundo cadastrado localmente utilizando seu ID."
    )
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteWorld(@PathVariable Long id){
            service.deleteWorld(id);
            return ResponseEntity.noContent().build();
    }



}
