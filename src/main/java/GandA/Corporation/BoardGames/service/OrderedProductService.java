package GandA.Corporation.BoardGames.service;

import GandA.Corporation.BoardGames.domain.OrderedProduct;
import GandA.Corporation.BoardGames.repo.OrderedProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderedProductService {
    @Autowired
    private OrderedProductRepository repo;

    public List<OrderedProduct> listAll() {
        return repo.findAll();
    }

    public void save(OrderedProduct product) {
        repo.save(product);
    }

    public OrderedProduct get(Long id) {
        return repo.findById(id).get();
    }

    public void delete(Long id) {
        repo.deleteById(id);
    }
}
