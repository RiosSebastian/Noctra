package Rios.tech.Noctra.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ContentRequestDTO {
    @NotBlank(message = "El título es obligatorio")
    private String title;
    @NotBlank(message = "La descripción es obligatoria")
    private String description;
    @NotBlank(message = "El género es obligatorio")
    private String genre;
    @NotBlank(message = "El tipo es obligatorio (MOVIE o SERIES)")
    private String type;
}