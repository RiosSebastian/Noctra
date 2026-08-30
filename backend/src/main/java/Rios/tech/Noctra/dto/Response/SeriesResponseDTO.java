package Rios.tech.Noctra.dto.Response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SeriesResponseDTO {
    private Long id;
    private String title;
    private String genre;
}
