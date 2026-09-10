package Rios.tech.Noctra.api.controller;


import Rios.tech.Noctra.api.dto.TmdbGenreDto;
import Rios.tech.Noctra.api.service.SeriesService;
import Rios.tech.Noctra.dto.Response.SeriesResponseDTO;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/series")
public class TmdbSeriesController {

    private final SeriesService seriesService;

    public TmdbSeriesController(SeriesService seriesService) {
        this.seriesService = seriesService;
    }

    @GetMapping("/popular")
    public List<SeriesResponseDTO> getPopularSeries(
            @RequestParam(defaultValue = "1") int page
    ) {
        return seriesService.getPopularSeries(page);
    }

    @GetMapping("/tmdb/{id}")
    public SeriesResponseDTO getSeriesDetails(@PathVariable Long id) {
        return seriesService.getSeriesDetails(id);
    }

    @GetMapping("/search")
    public List<SeriesResponseDTO> searchSeries(@RequestParam String query, @RequestParam(defaultValue = "1") int page) {
        return seriesService.searchSeries(query, page);
    }

    @GetMapping("/genres")
    public List<TmdbGenreDto> getGenres() {
        return seriesService.getGenres();
    }

    @GetMapping("/discover")
    public List<SeriesResponseDTO> discoverByGenre(@RequestParam Long genreId, @RequestParam(defaultValue = "1") int page) {
        return seriesService.getSeriesByGenre(genreId, page);
    }
}