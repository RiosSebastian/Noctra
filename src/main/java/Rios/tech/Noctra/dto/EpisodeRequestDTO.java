package Rios.tech.Noctra.dto;

import lombok.Data;

@Data
public class EpisodeRequestDTO {
    private String title;
    private Integer duration;
    private Integer episodeNumber;
    private Long seasonId;
}