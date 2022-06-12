package GandA.Corporation.BoardGames.service;

import GandA.Corporation.BoardGames.domain.Review;
import GandA.Corporation.BoardGames.repo.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewService {
    @Autowired
    private ReviewRepository repo;

    public List<Review> listAll() {
        return repo.findAll();
    }

    public void save(Review product) {
        repo.save(product);
    }

    public Review get(Long id) {
        return repo.findById(id).get();
    }

    public void delete(Long id) {
        repo.deleteById(id);
    }
}
