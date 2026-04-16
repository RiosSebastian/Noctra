package Rios.tech.Noctra.repository;

import Rios.tech.Noctra.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {

    List<Review> findByContentId(Long contentId);

    List<Review> findByUserId(Long userId);
}
