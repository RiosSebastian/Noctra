package Rios.tech.Noctra.service;

import Rios.tech.Noctra.dto.Response.ReviewResponseDTO;
import Rios.tech.Noctra.dto.ReviewRequestDTO;
import Rios.tech.Noctra.entity.User;

import java.util.List;

public interface ReviewService {
    ReviewResponseDTO create(User user, ReviewRequestDTO dto);
    List<ReviewResponseDTO> getByContent(Long contentId);
    List<ReviewResponseDTO> getByUser(Long userId);
    void delete(User user, Long reviewId);
}