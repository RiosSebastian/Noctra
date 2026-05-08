package Rios.tech.Noctra.mapper;

import Rios.tech.Noctra.dto.Response.SubscriptionResponseDTO;
import Rios.tech.Noctra.dto.SubscriptionRequestDTO;
import Rios.tech.Noctra.entity.Subscription;
import org.springframework.stereotype.Component;

@Component
public class SubscriptionMapper {

    public Subscription toEntity(SubscriptionRequestDTO dto){

        Subscription subscription = new Subscription();

        subscription.setType(dto.getType());

        return subscription;
    }

    public SubscriptionResponseDTO toResponse(Subscription subscription){

        return SubscriptionResponseDTO.builder()
                .id(subscription.getId())
                .type(subscription.getType())
                .startDate(subscription.getStartDate())
                .endDate(subscription.getEndDate())
                .build();
    }
}