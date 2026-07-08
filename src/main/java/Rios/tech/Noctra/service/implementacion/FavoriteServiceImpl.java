package Rios.tech.Noctra.service.implementacion;

import Rios.tech.Noctra.dto.FavoriteRequestDTO;
import Rios.tech.Noctra.dto.Response.FavoriteResponseDTO;
import Rios.tech.Noctra.entity.Content;
import Rios.tech.Noctra.entity.Favorite;
import Rios.tech.Noctra.entity.User;
import Rios.tech.Noctra.repository.ContentRepository;
import Rios.tech.Noctra.repository.FavoriteRepository;
import Rios.tech.Noctra.repository.UserRepository;
import Rios.tech.Noctra.service.FavoriteService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FavoriteServiceImpl implements FavoriteService {

    private final FavoriteRepository favoriteRepository;
    private final UserRepository userRepository;
    private final ContentRepository contentRepository;

    @Override
    public void addFavorite(User user, FavoriteRequestDTO dto){

        if(favoriteRepository
                .findByUserIdAndContentId(
                        user.getId(),
                        dto.getContentId())
                .isPresent()){

            throw new RuntimeException(
                    "Ya está en favoritos");
        }

        Content content =
                contentRepository
                        .findById(dto.getContentId())
                        .orElseThrow();

        Favorite favorite = new Favorite();

        favorite.setUser(user);

        favorite.setContent(content);

        favoriteRepository.save(favorite);

    }

    @Override
    public void removeFavorite(User user, Long contentId) {
        Favorite favorite = favoriteRepository
                .findByUserIdAndContentId(user, contentId)
                .orElseThrow(() -> new RuntimeException("No existe en favoritos"));

        favoriteRepository.delete(favorite);
    }

    @Override
    public List<FavoriteResponseDTO> getFavorites(Long userId) {
        return favoriteRepository.findByUserId(userId)
                .stream()
                .map(fav -> FavoriteResponseDTO.builder()
                        .id(fav.getId())
                        .contentId(fav.getContent().getId())
                        .title(fav.getContent().getTitle())
                        .build())
                .toList();
    }
}
