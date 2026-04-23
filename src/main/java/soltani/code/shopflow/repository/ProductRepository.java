package soltani.code.shopflow.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import soltani.code.shopflow.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {


}
