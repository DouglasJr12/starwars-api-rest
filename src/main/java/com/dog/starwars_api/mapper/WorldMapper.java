package com.dog.starwars_api.mapper;

import com.dog.starwars_api.dto.WorldRequest;
import com.dog.starwars_api.dto.WorldResponse;
import com.dog.starwars_api.model.WorldModel;
import org.springframework.stereotype.Component;

@Component
public class WorldMapper {
    public WorldModel toModel (WorldRequest request){
        WorldModel world = new WorldModel();
        world.setName(request.getName());
        world.setClimate(request.getClimate());
        world.setLand(request.getLand());
        world.setPopulation(request.getPopulation());

        return world;

    }

    public WorldResponse toResponse(WorldModel model){
        WorldResponse response = new WorldResponse();

        response.setId(model.getId());
        response.setName(model.getName());
        response.setClimate(model.getClimate());
        response.setLand(model.getLand());
        response.setPopulation(model.getPopulation());

        return response;
    }

}
