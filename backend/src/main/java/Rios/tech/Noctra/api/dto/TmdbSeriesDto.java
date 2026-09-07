package Rios.tech.Noctra.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public record TmdbSeriesDto(
        Long id,
        String name,
        String overview,
        @JsonProperty("poster_path") String posterPath,
        @JsonProperty("backdrop_path") String backdropPath,
        @JsonProperty("first_air_date") String firstAirDate,
        @JsonProperty("vote_average") Double voteAverage,
        @JsonProperty("vote_count") Integer voteCount,
        // Solo viene poblado en la respuesta de /tv/{id} (detalle), no en /tv/popular
        List<TmdbGenreDto> genres
) {
}