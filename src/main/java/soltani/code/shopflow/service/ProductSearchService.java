package soltani.code.shopflow.service;


import org.springframework.stereotype.Service;
import soltani.code.shopflow.entity.Product;
import soltani.code.shopflow.entity.ProductDocument;
import soltani.code.shopflow.repository.ProductSearchRepository;

import java.util.List;

@Service
public class ProductSearchService {

    private final ProductSearchRepository productSearchRepository;

    public ProductSearchService(ProductSearchRepository productSearchRepository)
    {
        this.productSearchRepository = productSearchRepository;
    }


    public void indexProduct(Product product)
    {
        ProductDocument productDocument = new ProductDocument();

        productDocument.setName(product.getName());
        productDocument.setId(product.getId());
        productDocument.setDescription(product.getDescription());
        productDocument.setPrice(product.getPrice());
        productDocument.setStock(product.getStock());

        productSearchRepository.save(productDocument);
    }

    public List<ProductDocument> searchProducts(String keyword)
    {
        return productSearchRepository
                .findByNameContainingOrDescriptionContaining(keyword, keyword);

    }
}
