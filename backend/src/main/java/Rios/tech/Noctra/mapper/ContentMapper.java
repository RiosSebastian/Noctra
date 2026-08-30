package Rios.tech.Noctra.mapper;

import Rios.tech.Noctra.dto.Response.ContentResponseDTO;
import Rios.tech.Noctra.entity.Content;
import Rios.tech.Noctra.entity.Movie;
import Rios.tech.Noctra.entity.Series;
import org.springframework.stereotype.Component;

@Component
public class ContentMapper {


    public ContentResponseDTO toResponse(Content content) {
            return ContentResponseDTO.builder()
                    .id(content.getId())
                    .title(content.getTitle())
                    .description(content.getDescription())
                    .genre(content.getGenre())
                    .build();

    }


}
