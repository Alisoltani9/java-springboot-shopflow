package soltani.code.shopflow.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import soltani.code.shopflow.entity.Order;
import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {

    List<Order> findByUserId(Long userid);
}
