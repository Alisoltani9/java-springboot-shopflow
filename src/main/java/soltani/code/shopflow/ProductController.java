package soltani.code.shopflow;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v2/products")
public class ProductController {

    private final ProductRepository productRepository;

    public ProductController(ProductRepository productRepository)
    {
        this.productRepository = productRepository;
    }

    @GetMapping()
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
