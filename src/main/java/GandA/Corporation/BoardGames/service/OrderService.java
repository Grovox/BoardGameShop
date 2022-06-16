package GandA.Corporation.BoardGames.service;

import GandA.Corporation.BoardGames.domain.Order;
import GandA.Corporation.BoardGames.repo.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {
    @Autowired
    private OrderRepository repo;

    public List<Order> listAll() {
        return repo.findAll();
    }

    public void save(Order product) {
        repo.save(product);
    }

    public Order get(Long id) {
        return repo.findById(id).get();
    }

    public void delete(Long id) {
        repo.deleteById(id);
    }

    public List<Order> findByIdContaining(Long id) {
        return repo.findByIdContaining(id);
    }
}
