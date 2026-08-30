package Rios.tech.Noctra.service;

import Rios.tech.Noctra.dto.Response.SeasonResponseDTO;
import Rios.tech.Noctra.dto.SeasonRequestDTO;

import java.util.List;

public interface SeasonService {
    SeasonResponseDTO create(SeasonRequestDTO dto);
    SeasonResponseDTO getById(Long id);
    List<SeasonResponseDTO> getBySeries(Long seriesId);
    void delete(Long id);
}