package mk.finki.ukim.mk.lab.web.controller;

import mk.finki.ukim.mk.lab.model.Balloon;
import mk.finki.ukim.mk.lab.model.Order;
import mk.finki.ukim.mk.lab.model.ShoppingCart;
import mk.finki.ukim.mk.lab.service.BalloonService;
import mk.finki.ukim.mk.lab.service.OrderService;
import mk.finki.ukim.mk.lab.service.ShoppingCartService;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.http.HttpResponse;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/orders")
public class OrderController {
    private final OrderService orderService;
    private final BalloonService balloonService;
    private final ShoppingCartService shoppingCartService;

    public OrderController(OrderService orderService, BalloonService balloonService, ShoppingCartService shoppingCartService) {
        this.orderService = orderService;
        this.balloonService = balloonService;
        this.shoppingCartService = shoppingCartService;
    }
    @GetMapping
    public String gatOrderPage(@RequestParam(required = false) String error, Model model){
        if (error != null && !error.isEmpty()) {
            model.addAttribute("hasError", true);
            model.addAttribute("error", error);
        }
        List<Order> orders = this.orderService.findAllOrders();
        model.addAttribute("orders", orders);
        return "userOrders";
    }
}
