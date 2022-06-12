package GandA.Corporation.BoardGames.repo;

import GandA.Corporation.BoardGames.domain.Bucket;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BucketRepository extends JpaRepository<Bucket, Long> {
}
