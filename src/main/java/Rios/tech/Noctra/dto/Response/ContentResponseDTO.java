package Rios.tech.Noctra.dto.Response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ContentResponseDTO {
    private Long id;
    private String title;
    private String description;
    private String genre;
}
