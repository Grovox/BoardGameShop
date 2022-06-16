package GandA.Corporation.BoardGames.repo;

import GandA.Corporation.BoardGames.domain.Product;
import GandA.Corporation.BoardGames.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findByNameContainingIgnoreCase(String name);

}
