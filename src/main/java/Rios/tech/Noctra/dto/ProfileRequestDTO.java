package Rios.tech.Noctra.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ProfileRequestDTO {

    @NotBlank(message = "El nombre es obligatorio")
    private String name;

    private String avatarUrl;
}