package Rios.tech.Noctra.Controllers;

import Rios.tech.Noctra.dto.Response.ReviewResponseDTO;
import Rios.tech.Noctra.dto.ReviewRequestDTO;
import Rios.tech.Noctra.entity.User;
import Rios.tech.Noctra.service.ReviewService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reviews")
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;

    @PostMapping
    public ResponseEntity<ReviewResponseDTO> create(@AuthenticationPrincipal User user,
                                                    @Valid @RequestBody ReviewRequestDTO dto) {
        return ResponseEntity.ok(reviewService.create(user, dto));
    }

    @GetMapping("/content/{contentId}")
    public ResponseEntity<List<ReviewResponseDTO>> getByContent(@PathVariable Long contentId) {
        return ResponseEntity.ok(reviewService.getByContent(contentId));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<ReviewResponseDTO>> getByUser(@PathVariable Long userId) {
        return ResponseEntity.ok(reviewService.getByUser(userId));
    }

    @DeleteMapping("/{reviewId}")
    public ResponseEntity<String> delete(@AuthenticationPrincipal User user,
                                         @PathVariable Long reviewId) {
        reviewService.delete(user, reviewId);
        return ResponseEntity.ok("Reseña eliminada");
    }
}
