package Rios.tech.Noctra.mapper;

import Rios.tech.Noctra.dto.Response.WatchlistResponseDTO;
import Rios.tech.Noctra.entity.Watchlist;
import org.springframework.stereotype.Component;

@Component
public class WatchlistMapper {

    public WatchlistResponseDTO toResponse(Watchlist watchlist){

        return WatchlistResponseDTO.builder()
                .id(watchlist.getId())
                .build();
    }
}