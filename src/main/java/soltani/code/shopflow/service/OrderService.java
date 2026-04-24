package soltani.code.shopflow.service;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import soltani.code.shopflow.dto.OrderEvent;
import soltani.code.shopflow.entity.Order;
import soltani.code.shopflow.repository.OrderRepository;

import java.util.List;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final KafkaTemplate<String, OrderEvent> orderEventKafka;

    public OrderService(OrderRepository orderRepository,
                        KafkaTemplate<String, OrderEvent> orderEventKafka)
    {
        this.orderRepository = orderRepository;
        this.orderEventKafka = orderEventKafka;
    }

    public Order save(Order order) {

        Order savedOrder = orderRepository.save(order);

        OrderEvent orderEvent = new OrderEvent(
                savedOrder.getId(),
                savedOrder.getUserId(),
                savedOrder.getTotalPrice());

        orderEventKafka.send("order-events", orderEvent);

        return savedOrder;

    }

    public List<Order> findByUserId(Long userId) {
        return orderRepository.findByUserId(userId);
    }

}
