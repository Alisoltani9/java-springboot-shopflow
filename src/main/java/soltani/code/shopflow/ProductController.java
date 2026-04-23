package soltani.code.shopflow;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v2/products")
public class ProductController {

    @GetMapping()
    public List<String> getProducts()
    {
        return List.of("test1", "test2", "test3");
    }

}
