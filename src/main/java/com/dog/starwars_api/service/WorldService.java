package com.dog.starwars_api.service;

import com.dog.starwars_api.dto.WorldRequest;
import com.dog.starwars_api.mapper.WorldMapper;
import com.dog.starwars_api.model.WorldModel;
import com.dog.starwars_api.repository.WorldRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WorldService {
    private final WorldRepository repository;
    private final WorldMapper mapper;

    public WorldService (WorldRepository repository, WorldMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public WorldModel createWorld(WorldRequest request) {

       WorldModel world = mapper.toModel(request);

       return repository.save(world);
    }


    public WorldModel findWorldById(Long id){
        return repository.findById(id).
                        orElseThrow(() -> new RuntimeException("Mundo não encontrado com id: " + id));
    }
    public List<WorldModel> findAllWorldsLocal(){
        List<WorldModel> worlds = repository.findAll();
        if(worlds.isEmpty()){
            throw new RuntimeException("Nenhum Mundo encontrado");
        }
        return worlds;
    }

    public WorldModel updateWorld (Long id, WorldRequest request){

        WorldModel world = findWorldById(id);

        world.setName(request.getName());
        world.setClimate(request.getClimate());
        world.setLand(request.getLand());
        world.setPopulation(request.getPopulation());


        return repository.save(world);
    }

    public void deleteWorld(Long id){
       if(!repository.existsById(id)){
            throw new IllegalArgumentException("ID informado não existe");
       }
       repository.deleteById(id);
    }




}
