package Rios.tech.Noctra.dto;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ReviewRequestDTO {
    @NotBlank(message = "El comentario es obligatorio")
    private String comment;

    @NotNull(message = "La calificación es obligatoria")
    @DecimalMin(value = "0.0", message = "La calificación mínima es 0")
    @DecimalMax(value = "5.0", message = "La calificación máxima es 5")
    private Double rating;

    @NotNull(message = "El contenido es obligatorio")
    private Long contentId;
}