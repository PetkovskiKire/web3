package mk.finki.ukim.mk.lab.web.controller;

import mk.finki.ukim.mk.lab.model.Order;
import mk.finki.ukim.mk.lab.service.OrderService;
import mk.finki.ukim.mk.lab.service.ShoppingCartService;
import mk.finki.ukim.mk.lab.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequestMapping("/shopping-cart")
public class ShoppingCartController {
    private final ShoppingCartService shoppingCartService;
    private final UserService userService;
    private final OrderService orderService;

    public ShoppingCartController(ShoppingCartService shoppingCartService, UserService userService, OrderService orderService) {
        this.shoppingCartService = shoppingCartService;
        this.userService = userService;
        this.orderService = orderService;
    }

    @GetMapping
    public String getShoppingPage(@RequestParam(required = false) String error, HttpServletRequest req, Model model) {
        if (error != null && !error.isEmpty()) {
            model.addAttribute("hasError", true);
            model.addAttribute("error", error);
        }
        String cartId = (String) req.getSession().getAttribute("cartId");
        String username = (String) req.getSession().getAttribute("username");

        List<Order> shoppingCart = this.shoppingCartService.listAllBalloonsInShoppingCart(Long.parseLong(cartId));
        model.addAttribute("shoppingCart", shoppingCart);
        model.addAttribute("cartId", cartId);

        return "shopingCart";
    }

    @PostMapping("/filter")
    public String filterOrders(@RequestParam String from, @RequestParam String to, HttpServletRequest req, Model model){

        List<Order> orders = this.orderService.filterTime(LocalDateTime.parse(from), LocalDateTime.parse(to));
        String cartId = (String) req.getSession().getAttribute("cartId");
        model.addAttribute("shoppingCart", orders);
        model.addAttribute("cartId", cartId);
        return "shopingCart";
    }

}
