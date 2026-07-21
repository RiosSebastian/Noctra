package Rios.tech.Noctra.Controllers;

import Rios.tech.Noctra.dto.Response.SubscriptionResponseDTO;
import Rios.tech.Noctra.dto.SubscriptionRequestDTO;
import Rios.tech.Noctra.entity.User;
import Rios.tech.Noctra.service.SubscriptionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/subscriptions")
@RequiredArgsConstructor
public class SubscriptionController {

    private final SubscriptionService subscriptionService;

    @PostMapping
    public ResponseEntity<SubscriptionResponseDTO> subscribe(@AuthenticationPrincipal User user,
                                                             @Valid @RequestBody SubscriptionRequestDTO dto) {
        return ResponseEntity.ok(subscriptionService.subscribe(user, dto));
    }

    @GetMapping("/{userId}")
    public ResponseEntity<SubscriptionResponseDTO> getByUser(@PathVariable Long userId) {
        return ResponseEntity.ok(subscriptionService.getByUser(userId));
    }

    @DeleteMapping
    public ResponseEntity<String> cancel(@AuthenticationPrincipal User user) {
        subscriptionService.cancel(user);
        return ResponseEntity.ok("Suscripción cancelada");
    }
}