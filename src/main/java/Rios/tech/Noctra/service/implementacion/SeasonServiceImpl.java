package Rios.tech.Noctra.service.implementacion;

import Rios.tech.Noctra.dto.Response.SeasonResponseDTO;
import Rios.tech.Noctra.dto.SeasonRequestDTO;
import Rios.tech.Noctra.entity.Season;
import Rios.tech.Noctra.entity.Series;
import Rios.tech.Noctra.exception.ContentNotFoundException;
import Rios.tech.Noctra.exception.SeasonNotFoundException;
import Rios.tech.Noctra.mapper.SeasonMapper;
import Rios.tech.Noctra.repository.SeasonRepository;
import Rios.tech.Noctra.repository.SeriesRepository;
import Rios.tech.Noctra.service.SeasonService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SeasonServiceImpl implements SeasonService {

    private final SeasonRepository seasonRepository;
    private final SeriesRepository seriesRepository;
    private final SeasonMapper seasonMapper;

    @Override
    public SeasonResponseDTO create(SeasonRequestDTO dto) {
        Series series = seriesRepository.findById(dto.getSeriesId())
                .orElseThrow(() -> new ContentNotFoundException("Serie no encontrada"));

        Season season = seasonMapper.toEntity(dto);
        season.setSeries(series); // <-- faltaba esta conexión
        season = seasonRepository.save(season);

        return seasonMapper.toResponse(season);
    }

    @Override
    public SeasonResponseDTO getById(Long id) {
        Season season = seasonRepository.findById(id)
                .orElseThrow(() -> new SeasonNotFoundException("Temporada no encontrada"));
        return seasonMapper.toResponse(season);
    }

    @Override
    public List<SeasonResponseDTO> getBySeries(Long seriesId) {
        return seasonRepository.findBySeriesId(seriesId)
                .stream()
                .map(seasonMapper::toResponse)
                .toList();
    }

    @Override
    public void delete(Long id) {
        Season season = seasonRepository.findById(id)
                .orElseThrow(() -> new SeasonNotFoundException("Temporada no encontrada"));
        seasonRepository.delete(season);
    }
}