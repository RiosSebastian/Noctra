package Rios.tech.Noctra.service.implementacion;

import Rios.tech.Noctra.dto.Response.WatchlistResponseDTO;
import Rios.tech.Noctra.dto.WatchlistRequestDTO;
import Rios.tech.Noctra.entity.Content;
import Rios.tech.Noctra.entity.Profile;
import Rios.tech.Noctra.entity.Watchlist;
import Rios.tech.Noctra.exception.ContentNotFoundException;
import Rios.tech.Noctra.exception.ProfileNotFoundException;
import Rios.tech.Noctra.exception.WatchlistNotFoundException;
import Rios.tech.Noctra.mapper.WatchlistMapper;
import Rios.tech.Noctra.repository.ContentRepository;
import Rios.tech.Noctra.repository.ProfileRepository;
import Rios.tech.Noctra.repository.WatchlistRepository;
import Rios.tech.Noctra.service.WatchlistService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class WatchlistServiceImpl implements WatchlistService {

    private final WatchlistRepository watchlistRepository;
    private final ProfileRepository profileRepository;
    private final ContentRepository contentRepository;
    private final WatchlistMapper watchlistMapper;

    @Override
    public WatchlistResponseDTO addToWatchlist(WatchlistRequestDTO dto) {
        Watchlist watchlist = getOrCreateWatchlist(dto.getProfileId());

        Content content = contentRepository.findById(dto.getContentId())
                .orElseThrow(() -> new ContentNotFoundException("Contenido no encontrado"));

        boolean yaExiste = watchlist.getContents()
                .stream()
                .anyMatch(c -> c.getId().equals(content.getId()));

        if (!yaExiste) {
            watchlist.getContents().add(content);
            watchlist = watchlistRepository.save(watchlist);
        }

        return watchlistMapper.toResponse(watchlist);
    }

    @Override
    public void removeFromWatchlist(Long profileId, Long contentId) {
        Watchlist watchlist = watchlistRepository.findByProfileId(profileId)
                .orElseThrow(() -> new WatchlistNotFoundException("Watchlist no encontrada"));

        watchlist.getContents().removeIf(c -> c.getId().equals(contentId));
        watchlistRepository.save(watchlist);
    }

    @Override
    public WatchlistResponseDTO getWatchlist(Long profileId) {
        Watchlist watchlist = watchlistRepository.findByProfileId(profileId)
                .orElseThrow(() -> new WatchlistNotFoundException("Watchlist no encontrada"));
        return watchlistMapper.toResponse(watchlist);
    }

    private Watchlist getOrCreateWatchlist(Long profileId) {
        return watchlistRepository.findByProfileId(profileId)
                .orElseGet(() -> {
                    Profile profile = profileRepository.findById(profileId)
                            .orElseThrow(() -> new ProfileNotFoundException("Perfil no encontrado"));
                    Watchlist nueva = new Watchlist();
                    nueva.setProfile(profile);
                    nueva.setContents(new ArrayList<>());
                    return watchlistRepository.save(nueva);
                });
    }
}