package Rios.tech.Noctra.dto.Response;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class SubscriptionResponseDTO {
    private Long id;
    private String type;
    private LocalDate startDate;
    private LocalDate endDate;
}