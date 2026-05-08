package Rios.tech.Noctra.mapper;

import Rios.tech.Noctra.dto.Response.SeasonResponseDTO;
import Rios.tech.Noctra.dto.SeasonRequestDTO;
import Rios.tech.Noctra.entity.Season;
import org.springframework.stereotype.Component;

@Component
public class SeasonMapper {

    public Season toEntity(SeasonRequestDTO dto){

        Season season = new Season();

        season.setNumbre(dto.getNumber());

        return season;
    }

    public SeasonResponseDTO toResponse(Season season){

        return SeasonResponseDTO.builder()
                .id(season.getId())
                .number(season.getNumbre())
                .build();
    }
}
