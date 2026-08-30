package Rios.tech.Noctra.mapper;

import Rios.tech.Noctra.dto.Response.FavoriteResponseDTO;
import Rios.tech.Noctra.entity.Favorite;
import org.springframework.stereotype.Component;

@Component
public class FavoriteMapper {

    public FavoriteResponseDTO toResponse(Favorite favorite){

        return FavoriteResponseDTO.builder()
                .id(favorite.getId())
                .contentId(favorite.getContent().getId())
                .title(favorite.getContent().getTitle())
                .build();
    }
}