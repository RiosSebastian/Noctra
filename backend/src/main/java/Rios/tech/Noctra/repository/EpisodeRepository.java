package Rios.tech.Noctra.repository;

import Rios.tech.Noctra.entity.Episode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EpisodeRepository extends JpaRepository<Episode, Long> {

    List<Episode> findBySeasonId(Long seasonId);
}