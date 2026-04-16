package Rios.tech.Noctra.dto.Response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class FavoriteResponseDTO {
    private Long id;
    private Long contentId;
    private String title;
}