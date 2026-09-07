package Rios.tech.Noctra.api.service;

import Rios.tech.Noctra.api.TmdbClient;
import Rios.tech.Noctra.api.mapper.TmdbMapper;
import Rios.tech.Noctra.dto.Response.SeriesResponseDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SeriesService {

    private final TmdbClient tmdbClient;
    private final TmdbMapper tmdbMapper;

    public SeriesService(TmdbClient tmdbClient, TmdbMapper tmdbMapper) {
        this.tmdbClient = tmdbClient;
        this.tmdbMapper = tmdbMapper;
    }

    public List<SeriesResponseDTO> getPopularSeries(int page) {
        return tmdbClient.getPopularSeries(page)
                .results()
                .stream()
                .map(tmdbMapper::toSeriesResponseDTO)
                .toList();
    }

    public SeriesResponseDTO getSeriesDetails(Long id) {
        return tmdbMapper.toSeriesResponseDTO(tmdbClient.getSeriesDetails(id));
    }
}