package Rios.tech.Noctra.repository;
import Rios.tech.Noctra.entity.Watchlist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WatchlistRepository extends JpaRepository<Watchlist, Long> {

    Optional<Watchlist> findByProfileId(Long profileId);
}
