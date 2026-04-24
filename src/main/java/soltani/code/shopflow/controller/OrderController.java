package soltani.code.shopflow.controller;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import soltani.code.shopflow.entity.Order;
import soltani.code.shopflow.entity.User;
import soltani.code.shopflow.repository.OrderRepository;
import soltani.code.shopflow.repository.UserRepository;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderRepository orderRepository;
    private final UserRepository userRepository;

    public OrderController(OrderRepository orderRepository,
                           UserRepository userRepository)
    {
        this.orderRepository = orderRepository;
        this.userRepository = userRepository;

    }

    @GetMapping("/my")
    public List<Order> getUserOrders()
    {
        String username = SecurityContextHolder.getContext()
                .getAuthentication().getName();

        User user = userRepository.findByUsername(username).orElseThrow();
        return orderRepository.findByUserId(user.getId());
    }

    @PostMapping
    public Order createOrder(@RequestBody Order order)
    {
        return orderRepository.save(order);
    }
}
