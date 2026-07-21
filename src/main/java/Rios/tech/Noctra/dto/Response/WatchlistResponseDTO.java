package Rios.tech.Noctra.dto.Response;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class WatchlistResponseDTO {
    private Long id;
    private Long profileId;
    private List<ContentResponseDTO> contents;
}