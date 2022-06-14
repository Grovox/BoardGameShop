package GandA.Corporation.BoardGames.service;

import GandA.Corporation.BoardGames.domain.Image;
import GandA.Corporation.BoardGames.domain.Product;
import GandA.Corporation.BoardGames.repo.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class ProductService {
    @Autowired
    private ProductRepository repo;

    public List<Product> listAll() {
        return repo.findAll();
    }

    public void save(Product product, MultipartFile file1, MultipartFile file2, MultipartFile file3, MultipartFile file4) throws IOException {
        Image image1;
        Image image2;
        Image image3;
        Image image4;
        if (file1.getSize() != 0) {
            image1 = toImageEntity(file1);
            image1.setIs_preview_images(true);
            product.addImageToProduct(image1);
        }

        if (file2.getSize() != 0) {
            image2 = toImageEntity(file2);
            product.addImageToProduct(image2);
        }

        if (file3.getSize() != 0) {
            image3 = toImageEntity(file3);
            product.addImageToProduct(image3);
        }

        if (file4.getSize() != 0) {
            image4 = toImageEntity(file4);
            product.addImageToProduct(image4);
        }
        Product productFromDb = repo.save(product);
        productFromDb.setPreview_image_id(productFromDb.getImages().get(0).getId());
        repo.save(product);
    }


    private Image toImageEntity(MultipartFile file) throws IOException {
        Image image = new Image();
        image.setName(file.getName());
        image.setOriginal_file_name(file.getOriginalFilename());
        image.setContent_type(file.getContentType());
        image.setSize(file.getSize());
        image.setBytes(file.getBytes());
        return image;
    }


    public Product get(Long id) {
        return repo.findById(id).get();
    }

    public void delete(Long id) {
        repo.deleteById(id);
    }
}
