package Rios.tech.Noctra.service;

import Rios.tech.Noctra.dto.FavoriteRequestDTO;
import Rios.tech.Noctra.dto.Response.FavoriteResponseDTO;

import java.util.List;

public interface FavoriteService {
    void addFavorite(FavoriteRequestDTO dto);

    void removeFavorite(Long userId, Long contentId);

    List<FavoriteResponseDTO> getFavorites(Long userId);
}
