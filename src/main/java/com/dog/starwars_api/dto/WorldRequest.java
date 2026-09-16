package com.dog.starwars_api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WorldRequest {

    @NotBlank(message ="Nome do mundo é obrigatório")
    @Size(min = 3, max = 50)
    private String name;
    @NotBlank(message = "Clima do mundo é obrigatório")
    @Size(min = 3, max = 50)
    private String climate;
    @NotBlank(message = "Terreno é obrigatório")
    @Size(min = 3, max = 50)
    private String land;
    @NotBlank(message = "População Do mundo é obrigatória")
    @Size(min = 3, max = 50)
    private String population;

}
