package Rios.tech.Noctra.dto;

import lombok.Data;

@Data
public class MovieRequestDTO {
    private String title;
    private String description;
    private String genre;
    private Integer duration;
}