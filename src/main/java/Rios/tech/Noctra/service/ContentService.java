package Rios.tech.Noctra.service;

import Rios.tech.Noctra.dto.ContentRequestDTO;
import Rios.tech.Noctra.dto.Response.ContentResponseDTO;

import java.util.List;

public interface ContentService {

    ContentResponseDTO create(ContentRequestDTO dto);

    List<ContentResponseDTO> getAll();

    ContentResponseDTO getById(Long id);

    List<ContentResponseDTO> searchByTitle(String title);

    List<ContentResponseDTO> getByGenre(String genre);
}