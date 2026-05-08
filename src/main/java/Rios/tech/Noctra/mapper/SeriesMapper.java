package Rios.tech.Noctra.mapper;

import Rios.tech.Noctra.dto.Response.SeriesResponseDTO;
import Rios.tech.Noctra.dto.SeriesRequestDTO;
import Rios.tech.Noctra.entity.Series;
import org.springframework.stereotype.Component;

@Component
public class SeriesMapper {

    public Series toEntity(SeriesRequestDTO dto){

        Series series = new Series();

        series.setTitle(dto.getTitle());
        series.setDescription(dto.getDescription());
        series.setGenre(dto.getGenre());

        return series;
    }

    public SeriesResponseDTO toResponse(Series series){

        return SeriesResponseDTO.builder()
                .id(series.getId())
                .title(series.getTitle())
                .genre(series.getGenre())
                .build();
    }

    public void updateEntity(Series series, SeriesRequestDTO dto){
        series.setTitle(dto.getTitle());
        series.setDescription(dto.getDescription());
        series.setGenre(dto.getGenre());
    }
}