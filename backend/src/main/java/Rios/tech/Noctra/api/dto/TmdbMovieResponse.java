package Rios.tech.Noctra.api.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;


public record TmdbMovieResponse(

        int page,

        List<TmdbMovieDto> results,

        @JsonProperty("total_pages")
        int totalPages,

        @JsonProperty("total_results")
        int totalResults

) {
}