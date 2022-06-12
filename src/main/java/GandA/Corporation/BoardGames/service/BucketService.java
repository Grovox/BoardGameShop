package GandA.Corporation.BoardGames.service;

import GandA.Corporation.BoardGames.domain.Bucket;
import GandA.Corporation.BoardGames.repo.BucketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BucketService {
    @Autowired
    private BucketRepository repo;

    public List<Bucket> listAll() {
        return repo.findAll();
    }

    public void save(Bucket product) {
        repo.save(product);
    }

    public Bucket get(Long id) {
        return repo.findById(id).get();
    }

    public void delete(Long id) {
        repo.deleteById(id);
    }
}
