package GandA.Corporation.BoardGames.service;

import GandA.Corporation.BoardGames.domain.Image;
import GandA.Corporation.BoardGames.repo.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ImageService {
    @Autowired
    private ImageRepository repo;

    public List<Image> listAll() {
        return repo.findAll();
    }

    public void save(Image product) {
        repo.save(product);
    }

    public Image get(Long id) {
        return repo.findById(id).get();
    }

    public void delete(Long id) {
        repo.deleteById(id);
    }
}
