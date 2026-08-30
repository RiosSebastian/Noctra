package Rios.tech.Noctra.mapper;

import Rios.tech.Noctra.dto.Response.SubscriptionResponseDTO;
import Rios.tech.Noctra.dto.SubscriptionRequestDTO;
import Rios.tech.Noctra.entity.Subscription;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class SubscriptionMapper {

    public Subscription toEntity(SubscriptionRequestDTO dto) {
        Subscription subscription = new Subscription();
        subscription.setType(dto.getType());
        subscription.setPrice(dto.getType().getPrice());
        subscription.setStartDate(LocalDate.now());
        subscription.setEndDate(LocalDate.now().plusMonths(dto.getType().getDurationMonths()));
        return subscription;
    }

    public SubscriptionResponseDTO toResponse(Subscription subscription) {
        return SubscriptionResponseDTO.builder()
                .id(subscription.getId())
                .type(subscription.getType())
                .price(subscription.getPrice())
                .startDate(subscription.getStartDate())
                .endDate(subscription.getEndDate())
                .build();
    }
}