package GandA.Corporation.BoardGames.repo;

import GandA.Corporation.BoardGames.domain.OrderedProduct;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderedProductRepository extends JpaRepository<OrderedProduct, Long> {
}
