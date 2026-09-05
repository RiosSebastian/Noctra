package Rios.tech.Noctra.api.controller;

import Rios.tech.Noctra.api.service.MovieService;
import Rios.tech.Noctra.dto.Response.MovieResponseDTO;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/movies")
public class TmdbMovieController {

    private final MovieService movieService;

    public TmdbMovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping("/popular")
    public List<MovieResponseDTO> getPopularMovies(@RequestParam(defaultValue = "1") int page) {
        return movieService.getPopularMovies(page);
    }

    @GetMapping("/tmdb/{id}")
    public MovieResponseDTO getMovieDetails(@PathVariable Long id) {
        return movieService.getMovieDetails(id);
    }

    @GetMapping("/search")
    public List<MovieResponseDTO> searchMovies(@RequestParam String query, @RequestParam(defaultValue = "1") int page) {
        return movieService.searchMovies(query, page);
    }
}
