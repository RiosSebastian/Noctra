package Rios.tech.Noctra.dto;

import Rios.tech.Noctra.util.SubscriptionType;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class SubscriptionRequestDTO {
    @NotNull(message = "El tipo de suscripción es obligatorio")
    private SubscriptionType type;
}