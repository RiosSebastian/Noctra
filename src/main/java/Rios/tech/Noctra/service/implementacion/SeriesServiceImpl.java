package Rios.tech.Noctra.service.implementacion;

import Rios.tech.Noctra.dto.Response.SeriesResponseDTO;
import Rios.tech.Noctra.dto.SeriesRequestDTO;
import Rios.tech.Noctra.entity.Series;
import Rios.tech.Noctra.exception.ContentNotFoundException;
import Rios.tech.Noctra.mapper.SeriesMapper;
import Rios.tech.Noctra.repository.SeriesRepository;
import Rios.tech.Noctra.service.SeriesService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SeriesServiceImpl implements SeriesService {

    private final SeriesRepository seriesRepository;
    private final SeriesMapper seriesMapper;

    @Override
    public SeriesResponseDTO create(SeriesRequestDTO dto) {
        Series series = seriesMapper.toEntity(dto);
        series = seriesRepository.save(series);
        return seriesMapper.toResponse(series);
    }

    @Override
    public SeriesResponseDTO getById(Long id) {
        Series series = seriesRepository.findById(id)
                .orElseThrow(() -> new ContentNotFoundException("Serie no encontrada"));
        return seriesMapper.toResponse(series);
    }

    @Override
    public List<SeriesResponseDTO> getAll() {
        return seriesRepository.findAll()
                .stream()
                .map(seriesMapper::toResponse)
                .toList();
    }

    @Override
    public SeriesResponseDTO update(Long id, SeriesRequestDTO dto) {
        Series series = seriesRepository.findById(id)
                .orElseThrow(() -> new ContentNotFoundException("Serie no encontrada"));
        seriesMapper.updateEntity(series, dto);
        series = seriesRepository.save(series);
        return seriesMapper.toResponse(series);
    }

    @Override
    public void delete(Long id) {
        Series series = seriesRepository.findById(id)
                .orElseThrow(() -> new ContentNotFoundException("Serie no encontrada"));
        seriesRepository.delete(series);
    }
}