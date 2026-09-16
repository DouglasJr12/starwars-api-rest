package com.dog.starwars_api.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class WorldResponse {
    private Long id;
    private String name;
    private String climate;
    private String land;
    private String population;

}
