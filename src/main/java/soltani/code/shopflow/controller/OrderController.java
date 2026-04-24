package soltani.code.shopflow.controller;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import soltani.code.shopflow.entity.Order;
import soltani.code.shopflow.entity.User;
import soltani.code.shopflow.repository.UserRepository;
import soltani.code.shopflow.service.OrderService;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderService orderService;
    private final UserRepository userRepository;

    public OrderController(OrderService orderService,
                           UserRepository userRepository)
    {
        this.orderService = orderService;
        this.userRepository = userRepository;

    }

    @GetMapping("/my")
    public List<Order> getUserOrders()
    {
        String username = SecurityContextHolder.getContext()
                .getAuthentication().getName();

        User user = userRepository.findByUsername(username).orElseThrow();
        return orderService.findByUserId(user.getId());
    }

    @PostMapping
    public Order createOrder(@RequestBody Order order)
    {
        return orderService.save(order);
    }
}
