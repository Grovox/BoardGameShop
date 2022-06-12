package GandA.Corporation.BoardGames.repo;

import GandA.Corporation.BoardGames.domain.Image;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepository extends JpaRepository<Image, Long> {
}
