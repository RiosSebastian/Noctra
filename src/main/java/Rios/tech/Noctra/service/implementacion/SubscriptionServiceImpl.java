package Rios.tech.Noctra.service.implementacion;

import Rios.tech.Noctra.dto.Response.SubscriptionResponseDTO;
import Rios.tech.Noctra.dto.SubscriptionRequestDTO;
import Rios.tech.Noctra.entity.Subscription;
import Rios.tech.Noctra.entity.User;
import Rios.tech.Noctra.exception.SubscriptionNotFoundException;
import Rios.tech.Noctra.mapper.SubscriptionMapper;
import Rios.tech.Noctra.repository.SubscriptionRepository;
import Rios.tech.Noctra.repository.UserRepository;
import Rios.tech.Noctra.service.SubscriptionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SubscriptionServiceImpl implements SubscriptionService {

    private final SubscriptionRepository subscriptionRepository;
    private final UserRepository userRepository;
    private final SubscriptionMapper subscriptionMapper;

    @Override
    public SubscriptionResponseDTO subscribe(User user, SubscriptionRequestDTO dto) {
        subscriptionRepository.findByUserId(user.getId())
                .ifPresent(s -> {
                    throw new IllegalStateException("El usuario ya tiene una suscripción activa");
                });

        Subscription subscription = subscriptionMapper.toEntity(dto);
        subscription = subscriptionRepository.save(subscription);

        user.setSubscription(subscription);
        userRepository.save(user);

        return subscriptionMapper.toResponse(subscription);
    }

    @Override
    public SubscriptionResponseDTO getByUser(Long userId) {
        Subscription subscription = subscriptionRepository.findByUserId(userId)
                .orElseThrow(() -> new SubscriptionNotFoundException("El usuario no tiene suscripción"));
        return subscriptionMapper.toResponse(subscription);
    }

    @Override
    public void cancel(User user) {
        Subscription subscription = subscriptionRepository.findByUserId(user.getId())
                .orElseThrow(() -> new SubscriptionNotFoundException("El usuario no tiene suscripción"));
        user.setSubscription(null);
        userRepository.save(user);
        subscriptionRepository.delete(subscription);
    }
}