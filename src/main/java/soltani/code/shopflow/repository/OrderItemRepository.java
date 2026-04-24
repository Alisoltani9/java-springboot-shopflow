package soltani.code.shopflow.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import soltani.code.shopflow.entity.OrderItem;

public interface OrderItemRepository extends JpaRepository<OrderItem,Long> {
}
