package Rios.tech.Noctra.service;

import Rios.tech.Noctra.dto.FavoriteRequestDTO;
import Rios.tech.Noctra.dto.Response.FavoriteResponseDTO;
import Rios.tech.Noctra.entity.User;

import java.util.List;

public interface FavoriteService {
    public void addFavorite(User user, FavoriteRequestDTO dto);

    public void removeFavorite(User user, Long contentId);

    List<FavoriteResponseDTO> getFavorites(Long userId);
}
