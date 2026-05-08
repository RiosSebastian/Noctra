package Rios.tech.Noctra.mapper;

import Rios.tech.Noctra.dto.Response.ReviewResponseDTO;
import Rios.tech.Noctra.dto.ReviewRequestDTO;
import Rios.tech.Noctra.entity.Review;
import org.springframework.stereotype.Component;

@Component
public class ReviewMapper {

    public Review toEntity(ReviewRequestDTO dto){

        Review review = new Review();

        review.setComment(dto.getComment());
        review.setRating(dto.getRating());

        return review;
    }

    public ReviewResponseDTO toResponse(Review review){

        return ReviewResponseDTO.builder()
                .id(review.getId())
                .comment(review.getComment())
                .rating(review.getRating())
                .username(review.getUser().getUsername())
                .build();
    }
}