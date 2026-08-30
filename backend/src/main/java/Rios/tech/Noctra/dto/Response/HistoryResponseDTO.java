package Rios.tech.Noctra.dto.Response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class HistoryResponseDTO {
    private Long id;
    private Double progress;
    private String title;
}