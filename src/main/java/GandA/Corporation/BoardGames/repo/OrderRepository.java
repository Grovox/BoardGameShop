package GandA.Corporation.BoardGames.repo;

import GandA.Corporation.BoardGames.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository  extends JpaRepository<Order, Long> {

    List<Order> findByIdContaining(Long id);

}
