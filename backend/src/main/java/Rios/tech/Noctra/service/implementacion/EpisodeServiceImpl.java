package Rios.tech.Noctra.service.implementacion;

import Rios.tech.Noctra.dto.EpisodeRequestDTO;
import Rios.tech.Noctra.dto.Response.EpisodeResponseDTO;
import Rios.tech.Noctra.entity.Episode;
import Rios.tech.Noctra.entity.Season;
import Rios.tech.Noctra.exception.EpisodeNotFoundException;
import Rios.tech.Noctra.exception.SeasonNotFoundException;
import Rios.tech.Noctra.mapper.EpisodeMapper;
import Rios.tech.Noctra.repository.EpisodeRepository;
import Rios.tech.Noctra.repository.SeasonRepository;
import Rios.tech.Noctra.service.EpisodeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EpisodeServiceImpl implements EpisodeService {

    private final EpisodeRepository episodeRepository;
    private final SeasonRepository seasonRepository;
    private final EpisodeMapper episodeMapper;

    @Override
    public EpisodeResponseDTO create(EpisodeRequestDTO dto) {
        Season season = seasonRepository.findById(dto.getSeasonId())
                .orElseThrow(() -> new SeasonNotFoundException("Temporada no encontrada"));

        Episode episode = episodeMapper.toEntity(dto);
        episode.setSeason(season); // <-- faltaba esta conexión
        episode = episodeRepository.save(episode);

        return episodeMapper.toResponse(episode);
    }

    @Override
    public EpisodeResponseDTO getById(Long id) {
        Episode episode = episodeRepository.findById(id)
                .orElseThrow(() -> new EpisodeNotFoundException("Episodio no encontrado"));
        return episodeMapper.toResponse(episode);
    }

    @Override
    public List<EpisodeResponseDTO> getBySeason(Long seasonId) {
        return episodeRepository.findBySeasonId(seasonId)
                .stream()
                .map(episodeMapper::toResponse)
                .toList();
    }

    @Override
    public void delete(Long id) {
        Episode episode = episodeRepository.findById(id)
                .orElseThrow(() -> new EpisodeNotFoundException("Episodio no encontrado"));
        episodeRepository.delete(episode);
    }
}