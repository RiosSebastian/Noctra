package Rios.tech.Noctra.service.implementacion;

import Rios.tech.Noctra.dto.Response.ReviewResponseDTO;
import Rios.tech.Noctra.dto.ReviewRequestDTO;
import Rios.tech.Noctra.entity.Content;
import Rios.tech.Noctra.entity.Review;
import Rios.tech.Noctra.entity.User;
import Rios.tech.Noctra.exception.ContentNotFoundException;
import Rios.tech.Noctra.exception.ReviewNotFoundException;
import Rios.tech.Noctra.mapper.ReviewMapper;
import Rios.tech.Noctra.repository.ContentRepository;
import Rios.tech.Noctra.repository.ReviewRepository;
import Rios.tech.Noctra.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.nio.file.AccessDeniedException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;
    private final ContentRepository contentRepository;
    private final ReviewMapper reviewMapper;

    @Override
    public ReviewResponseDTO create(User user, ReviewRequestDTO dto) {
        Content content = contentRepository.findById(dto.getContentId())
                .orElseThrow(() -> new ContentNotFoundException("Contenido no encontrado"));

        Review review = reviewMapper.toEntity(dto);
        review.setUser(user);
        review.setContent(content);
        review = reviewRepository.save(review);

        return reviewMapper.toResponse(review);
    }

    @Override
    public List<ReviewResponseDTO> getByContent(Long contentId) {
        return reviewRepository.findByContentId(contentId)
                .stream()
                .map(reviewMapper::toResponse)
                .toList();
    }

    @Override
    public List<ReviewResponseDTO> getByUser(Long userId) {
        return reviewRepository.findByUserId(userId)
                .stream()
                .map(reviewMapper::toResponse)
                .toList();
    }

    @Override
    public void delete(User user, Long reviewId) {
        Review review = reviewRepository.findById(reviewId)
                .orElseThrow(() -> new ReviewNotFoundException("Reseña no encontrada"));

        if (!review.getUser().getId().equals(user.getId())) {
            throw new AccessDeniedException("No podés eliminar la reseña de otro usuario");
        }
        reviewRepository.delete(review);
    }
}