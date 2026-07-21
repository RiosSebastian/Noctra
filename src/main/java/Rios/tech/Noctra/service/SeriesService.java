package Rios.tech.Noctra.service;

import Rios.tech.Noctra.dto.Response.SeriesResponseDTO;
import Rios.tech.Noctra.dto.SeriesRequestDTO;

import java.util.List;

public interface SeriesService {
    SeriesResponseDTO create(SeriesRequestDTO dto);
    SeriesResponseDTO getById(Long id);
    List<SeriesResponseDTO> getAll();
    SeriesResponseDTO update(Long id, SeriesRequestDTO dto);
    void delete(Long id);
}