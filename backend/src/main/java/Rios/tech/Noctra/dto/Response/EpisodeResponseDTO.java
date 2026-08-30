package Rios.tech.Noctra.dto.Response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class EpisodeResponseDTO {
    private Long id;
    private String title;
    private Integer duration;
    private Integer episodeNumber;
}