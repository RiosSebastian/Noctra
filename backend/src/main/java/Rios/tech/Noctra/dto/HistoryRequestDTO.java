package Rios.tech.Noctra.dto;

import lombok.Data;

@Data
public class HistoryRequestDTO {
    private Long profileId;
    private Long contentId;
    private Double progress;
}