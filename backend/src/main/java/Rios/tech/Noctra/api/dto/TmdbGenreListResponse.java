package Rios.tech.Noctra.api.dto;

import java.util.List;

public record TmdbGenreListResponse(
        List<TmdbGenreDto> genres
) {
}