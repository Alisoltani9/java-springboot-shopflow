package soltani.code.shopflow.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import soltani.code.shopflow.dto.OrderEvent;
import soltani.code.shopflow.entity.Order;
import soltani.code.shopflow.repository.OrderRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;


    public Order save(Order order) {

        return orderRepository.save(order);



    }

    public List<Order> findByUserId(Long userId) {
        return orderRepository.findByUserId(userId);
    }

}
