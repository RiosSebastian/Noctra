package Rios.tech.Noctra.api.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public record TmdbSeriesResponse(
        int page,
        List<TmdbSeriesDto> results,
        @JsonProperty("total_pages") int totalPages,
        @JsonProperty("total_results") int totalResults
) {
}