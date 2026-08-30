package Rios.tech.Noctra.repository;

import Rios.tech.Noctra.entity.Season;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SeasonRepository extends JpaRepository<Season, Long> {

    List<Season> findBySeriesId(Long seriesId);
}
