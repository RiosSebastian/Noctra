package Rios.tech.Noctra.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public record TmdbMovieDto(
        Long id,
        String title,
        String overview,
        @JsonProperty("poster_path") String posterPath,
        @JsonProperty("backdrop_path") String backdropPath,
        @JsonProperty("release_date") String releaseDate,
        @JsonProperty("vote_average") Double voteAverage,
        @JsonProperty("vote_count") Integer voteCount,
        Integer runtime,
        List<TmdbGenreDto> genres
) {
}