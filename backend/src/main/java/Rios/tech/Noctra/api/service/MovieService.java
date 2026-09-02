package Rios.tech.Noctra.api.service;

import Rios.tech.Noctra.api.TmdbClient;
import Rios.tech.Noctra.api.mapper.TmdbMapper;
import Rios.tech.Noctra.dto.Response.MovieResponseDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieService {

    private final TmdbClient tmdbClient;
    private final TmdbMapper tmdbMapper;

    public MovieService(TmdbClient tmdbClient, TmdbMapper tmdbMapper) {
        this.tmdbClient = tmdbClient;
        this.tmdbMapper = tmdbMapper;
    }

    public List<MovieResponseDTO> getPopularMovies(int page) {
        return tmdbClient.getPopularMovies(page)
                .results()
                .stream()
                .map(tmdbMapper::toMovieResponseDTO)
                .toList();
    }
}