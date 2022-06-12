package GandA.Corporation.BoardGames.repo;

import GandA.Corporation.BoardGames.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
