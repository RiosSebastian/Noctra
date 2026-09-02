package Rios.tech.Noctra.dto.Response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MovieResponseDTO {
    private Long id;
    private String title;
    private String genre;
    private Integer duration;
    private String overview;
    private String posterUrl;
    private String backdropUrl;
    private String releaseDate;
    private Double rating;
}