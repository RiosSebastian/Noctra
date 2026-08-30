package Rios.tech.Noctra.service;

import Rios.tech.Noctra.dto.MovieRequestDTO;
import Rios.tech.Noctra.dto.Response.MovieResponseDTO;

import java.util.List;

public interface MovieService {
    MovieResponseDTO create(MovieRequestDTO dto);
    MovieResponseDTO getById(Long id);
    List<MovieResponseDTO> getAll();
    MovieResponseDTO update(Long id, MovieRequestDTO dto);
    void delete(Long id);
}
