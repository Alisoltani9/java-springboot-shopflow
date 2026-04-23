package soltani.code.shopflow.controller;

import org.springframework.web.bind.annotation.*;
import soltani.code.shopflow.entity.Product;
import soltani.code.shopflow.repository.ProductRepository;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductRepository productRepository;

    public ProductController(ProductRepository productRepository)
    {
        this.productRepository = productRepository;
    }

    @GetMapping
    public List<Product> getProducts()
    {
       return productRepository.findAll();
    }

    @GetMapping("/{id}")
    public Product getProductById(@PathVariable Long id)
    {
        return productRepository.findById(id).orElseThrow();
    }

    @PostMapping
    public Product save(@RequestBody Product product)
    {
        return productRepository.save(product);
    }


}
