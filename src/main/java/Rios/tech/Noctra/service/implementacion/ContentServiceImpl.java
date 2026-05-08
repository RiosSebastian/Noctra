package Rios.tech.Noctra.service.implementacion;

import Rios.tech.Noctra.dto.ContentRequestDTO;
import Rios.tech.Noctra.dto.Response.ContentResponseDTO;
import Rios.tech.Noctra.entity.Content;
import Rios.tech.Noctra.mapper.ContentMapper;
import Rios.tech.Noctra.repository.ContentRepository;
import Rios.tech.Noctra.service.ContentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ContentServiceImpl implements ContentService {

    private final ContentRepository contentRepository;
    private final ContentMapper contentMapper;

    @Override
    public ContentResponseDTO create(ContentRequestDTO dto) {
        Content content = contentMapper.toEntity(dto);
        return contentMapper.toResponse(content);
    }

    @Override
    public List<ContentResponseDTO> getAll() {
        return contentRepository.findAll()
                .stream()
                .map(contentMapper::toResponse)
                .toList();
    }

    @Override
    public ContentResponseDTO getById(Long id) {
        Content content = contentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Contenido no encontrado"));
        return contentMapper.toResponse(content);
    }

    @Override
    public List<ContentResponseDTO> searchByTitle(String title) {
        return contentRepository.findByTitleContainingIgnoreCase(title)
                .stream()
                .map(contentMapper::toResponse)
                .toList();
    }

    @Override
    public List<ContentResponseDTO> getByGenre(String genre) {
        return contentRepository.findByGenre(genre)
                .stream()
                .map(contentMapper::toResponse)
                .toList();
    }
}
