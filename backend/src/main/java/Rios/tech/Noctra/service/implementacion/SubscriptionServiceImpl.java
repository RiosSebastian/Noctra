package Rios.tech.Noctra.service.implementacion;

import Rios.tech.Noctra.dto.Response.SubscriptionResponseDTO;
import Rios.tech.Noctra.dto.SubscriptionRequestDTO;
import Rios.tech.Noctra.entity.Subscription;
import Rios.tech.Noctra.entity.User;
import Rios.tech.Noctra.exception.SubscriptionExpiredException;
import Rios.tech.Noctra.exception.SubscriptionNotFoundException;
import Rios.tech.Noctra.mapper.SubscriptionMapper;
import Rios.tech.Noctra.repository.SubscriptionRepository;
import Rios.tech.Noctra.repository.UserRepository;
import Rios.tech.Noctra.service.SubscriptionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class SubscriptionServiceImpl implements SubscriptionService {

    private final SubscriptionRepository subscriptionRepository;
    private final UserRepository userRepository;
    private final SubscriptionMapper subscriptionMapper;

    @Override
    public SubscriptionResponseDTO subscribe(User user, SubscriptionRequestDTO dto) {
        subscriptionRepository.findByUserId(user.getId()).ifPresent(existing -> {
            if (!isExpired(existing)) {
                throw new IllegalStateException("El usuario ya tiene una suscripción activa");
            }
            // si venció, la reemplazamos en vez de bloquear
            subscriptionRepository.delete(existing);
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

        if (isExpired(subscription)) {
            throw new SubscriptionExpiredException("La suscripción venció el " + subscription.getEndDate());
        }
        return subscriptionMapper.toResponse(subscription);
    }

    @Override
    public SubscriptionResponseDTO renew(User user) {
        Subscription subscription = subscriptionRepository.findByUserId(user.getId())
                .orElseThrow(() -> new SubscriptionNotFoundException("El usuario no tiene suscripción"));

        LocalDate base = isExpired(subscription) ? LocalDate.now() : subscription.getEndDate();
        subscription.setStartDate(isExpired(subscription) ? LocalDate.now() : subscription.getStartDate());
        subscription.setEndDate(base.plusMonths(subscription.getType().getDurationMonths()));

        subscription = subscriptionRepository.save(subscription);
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

    private boolean isExpired(Subscription subscription) {
        return subscription.getEndDate().isBefore(LocalDate.now());
    }
}