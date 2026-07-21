package Rios.tech.Noctra.service;

import Rios.tech.Noctra.dto.Response.SubscriptionResponseDTO;
import Rios.tech.Noctra.dto.SubscriptionRequestDTO;
import Rios.tech.Noctra.entity.User;

public interface SubscriptionService {
    SubscriptionResponseDTO subscribe(User user, SubscriptionRequestDTO dto);
    SubscriptionResponseDTO getByUser(Long userId);
    void cancel(User user);
}