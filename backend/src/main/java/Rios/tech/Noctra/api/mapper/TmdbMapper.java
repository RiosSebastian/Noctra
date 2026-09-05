package Rios.tech.Noctra.api.mapper;

import Rios.tech.Noctra.api.config.TmdbConfig;
import Rios.tech.Noctra.api.dto.TmdbMovieDto;
import Rios.tech.Noctra.dto.Response.MovieResponseDTO;
import org.springframework.stereotype.Component;

@Component
public class TmdbMapper {

    private final String imageBaseUrl;

    public TmdbMapper(TmdbConfig config) {
        // TMDB devuelve solo el path ("/abc123.jpg"), no la URL completa,
        // así que hay que armarla acá. w500 es un buen tamaño para cards de catálogo.
        this.imageBaseUrl = config.imageBaseUrl() != null
                ? config.imageBaseUrl()
                : "https://image.tmdb.org/t/p/w500";
    }

    public MovieResponseDTO toMovieResponseDTO(TmdbMovieDto dto) {
        return MovieResponseDTO.builder()
                .id(dto.id())
                .title(dto.title())
                .overview(dto.overview())
                .posterUrl(buildImageUrl(dto.posterPath()))
                .backdropUrl(buildImageUrl(dto.backdropPath()))
                .releaseDate(dto.releaseDate())
                .rating(dto.voteAverage())
                .genre(dto.genres() != null && !dto.genres().isEmpty()
                        ? dto.genres().get(0).name()
                        : null)
                .duration(dto.runtime())
                .build();
    }

    private String buildImageUrl(String path) {
        return path != null ? imageBaseUrl + path : null;
    }
}