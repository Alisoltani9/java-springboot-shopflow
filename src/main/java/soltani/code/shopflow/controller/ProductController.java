package soltani.code.shopflow.controller;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.web.bind.annotation.*;
import soltani.code.shopflow.entity.Product;
import soltani.code.shopflow.repository.ProductRepository;
import soltani.code.shopflow.service.ProductService;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductRepository productRepository;
    private final ProductService productService;

    public ProductController(ProductRepository productRepository, ProductService productService)
    {
        this.productRepository = productRepository;
        this.productService = productService;
    }

    @GetMapping
    public List<Product> getProducts()
    {
        return productService.getAllProducts();
    }

    @GetMapping("/{id}")
    public Product getProductById(@PathVariable Long id)
    {
        return productRepository.findById(id).orElseThrow();
    }

    @PostMapping
    @CacheEvict(value = "products", allEntries = true)
    public Product save(@RequestBody Product product)
    {
        return productRepository.save(product);
    }


}
