package GandA.Corporation.BoardGames.repo;

import GandA.Corporation.BoardGames.domain.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review, Long> {
}
