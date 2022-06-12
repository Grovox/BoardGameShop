package GandA.Corporation.BoardGames.repo;

import GandA.Corporation.BoardGames.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository  extends JpaRepository<Order, Long> {
}
