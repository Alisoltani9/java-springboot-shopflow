package soltani.code.shopflow.controller;

import org.springframework.web.bind.annotation.*;
import soltani.code.shopflow.entity.Product;
import soltani.code.shopflow.service.ProductService;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService)
    {
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
        return productService.getProductById(id);
    }

    @PostMapping
    public Product save(@RequestBody Product product)
    {
        return productService.save(product);
    }


}
