package Rios.tech.Noctra.mapper;

import Rios.tech.Noctra.dto.MovieRequestDTO;
import Rios.tech.Noctra.dto.Response.MovieResponseDTO;
import Rios.tech.Noctra.entity.Movie;
import org.springframework.stereotype.Component;

@Component
public class MovieMapper {

    public Movie toEntity(MovieRequestDTO dto){
        Movie movie = new Movie();
        movie.setTitle(dto.getTitle());
        movie.setDescription(dto.getDescription());
        movie.setGenre(dto.getGenre());
        movie.setDuration(dto.getDuration());
        return movie;
    }

    public MovieResponseDTO toDTO(Movie movie){
        return MovieResponseDTO.builder()
                .id(movie.getId())
                .title(movie.getTitle())
                .genre(movie.getGenre())
                .duration(movie.getDuration())
                .build();
    }
}