package mk.finki.ukim.mk.lab.web.controller;

import mk.finki.ukim.mk.lab.model.ShoppingCart;
import mk.finki.ukim.mk.lab.service.ShoppingCartService;
import mk.finki.ukim.mk.lab.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;

@Controller
@RequestMapping("/user")
public class UserController {
     private final ShoppingCartService shoppingCartService;
     private final UserService userService;

    public UserController(ShoppingCartService shoppingCartService, UserService userService) {
        this.shoppingCartService = shoppingCartService;
        this.userService = userService;
    }
    @GetMapping
    public String getUserPage(@RequestParam(required = false) String error, Model model) {

        if (error != null && !error.isEmpty()) {
            model.addAttribute("hasError", true);
            model.addAttribute("error", error);
        }

        return "deliveryInfo";
    }

    @PostMapping("/active")
    public String creteShoppingcart( @RequestParam String username, @RequestParam String date,  HttpServletRequest req){

        ShoppingCart shoppingCart = this.shoppingCartService.getActiveShoppingCart(username, LocalDateTime.parse(date));
        String cartId = String.valueOf(shoppingCart.getId());

        req.getSession().setAttribute("cartId", cartId);
        req.getSession().setAttribute("username", username);

        return "redirect:/balloon";
    }
}
