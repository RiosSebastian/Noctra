package Rios.tech.Noctra.dto;

import lombok.Data;

@Data
public class ReviewRequestDTO {
    private String comment;
    private Double rating;
    private Long contentId;
}