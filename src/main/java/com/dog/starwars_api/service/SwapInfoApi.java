package com.dog.starwars_api.service;

import com.dog.starwars_api.dto.SwapInfoDto;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


@Service
public class SwapInfoApi {
    public SwapInfoDto getWorldsApi(Long id) {

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<SwapInfoDto> response = restTemplate
                .getForEntity("https://swapi.info/api/planets/" + id, SwapInfoDto.class);
        return response.getBody();

    }
}
