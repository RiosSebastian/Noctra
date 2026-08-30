package Rios.tech.Noctra.service;

import Rios.tech.Noctra.dto.Response.WatchlistResponseDTO;
import Rios.tech.Noctra.dto.WatchlistRequestDTO;

public interface WatchlistService {
    WatchlistResponseDTO addToWatchlist(WatchlistRequestDTO dto);
    void removeFromWatchlist(Long profileId, Long contentId);
    WatchlistResponseDTO getWatchlist(Long profileId);
}