package soltani.code.shopflow.service;

import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import soltani.code.shopflow.entity.Product;
import soltani.code.shopflow.exception.ResourceNotFoundException;
import soltani.code.shopflow.repository.ProductRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;



    @Cacheable("products")
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @CacheEvict(value = {"products", "product"}, allEntries = true)
    public Product save(Product product) {

        return productRepository.save(product);

    }

    @Cacheable(value = "product" , key = "#productId")
    public Product getProductById(Long productId) {
        return productRepository.findById(productId).orElseThrow
                (() -> new ResourceNotFoundException
                        ("Product not found with id: " + productId));

    }
}