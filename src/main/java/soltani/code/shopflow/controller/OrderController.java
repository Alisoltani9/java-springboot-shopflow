package soltani.code.shopflow.controller;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import soltani.code.shopflow.entity.Order;
import soltani.code.shopflow.service.OrderService;
import soltani.code.shopflow.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderService orderService;
    private final UserService userService;
    public OrderController(OrderService orderService,
                           UserService userService)
    {
        this.orderService = orderService;
        this.userService = userService;

    }

    @GetMapping("/my")
    public List<Order> getUserOrders()
    {
        String username = SecurityContextHolder.getContext()
                .getAuthentication().getName();

        Long userId = userService.findUserIdByUsername(username);
        return orderService.findByUserId(userId);

    }

    @PostMapping
    public Order createOrder(@RequestBody Order order)
    {
        return orderService.save(order);
    }
}
