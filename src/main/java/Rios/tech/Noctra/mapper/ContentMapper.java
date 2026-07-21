package Rios.tech.Noctra.mapper;

import Rios.tech.Noctra.dto.ContentRequestDTO;

import Rios.tech.Noctra.dto.Response.ContentResponseDTO;
import Rios.tech.Noctra.entity.Content;
import Rios.tech.Noctra.entity.Movie;
import Rios.tech.Noctra.entity.Series;
import org.springframework.stereotype.Component;

@Component
public class ContentMapper {

    public Content toEntity(ContentRequestDTO dto) {
        Content content = "SERIES".equalsIgnoreCase(dto.getType())
                ? new Series()
                : new Movie();
        content.setTitle(dto.getTitle());
        content.setDescription(dto.getDescription());
        content.setGenre(dto.getGenre());
        return content;
    }

    public ContentResponseDTO toResponse(Content content) {
        return ContentResponseDTO.builder()
                .id(content.getId())
                .title(content.getTitle())
                .description(content.getDescription())
                .genre(content.getGenre())
                .build();
    }

    public void updateEntity(Content content, ContentRequestDTO dto){
        content.setTitle(dto.getTitle());
        content.setDescription(dto.getDescription());
        content.setGenre(dto.getGenre());
    }
}
