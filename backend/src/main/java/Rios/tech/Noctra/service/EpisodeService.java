package Rios.tech.Noctra.service;

import Rios.tech.Noctra.dto.EpisodeRequestDTO;
import Rios.tech.Noctra.dto.Response.EpisodeResponseDTO;

import java.util.List;

public interface EpisodeService {
    EpisodeResponseDTO create(EpisodeRequestDTO dto);
    EpisodeResponseDTO getById(Long id);
    List<EpisodeResponseDTO> getBySeason(Long seasonId);
    void delete(Long id);
}