package Rios.tech.Noctra.dto.Response;

import Rios.tech.Noctra.util.SubscriptionType;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Builder
public class SubscriptionResponseDTO {
    private Long id;
    private SubscriptionType type;
    private BigDecimal price;
    private LocalDate startDate;
    private LocalDate endDate;
}