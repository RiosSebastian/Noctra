package Rios.tech.Noctra.mapper;

import Rios.tech.Noctra.dto.Response.WatchlistResponseDTO;
import Rios.tech.Noctra.entity.Watchlist;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class WatchlistMapper {

    private final ContentMapper contentMapper;

    public WatchlistResponseDTO toResponse(Watchlist watchlist) {
        return WatchlistResponseDTO.builder()
                .id(watchlist.getId())
                .profileId(watchlist.getProfile().getId())
                .contents(watchlist.getContents()
                        .stream()
                        .map(contentMapper::toResponse)
                        .toList())
                .build();
    }
}