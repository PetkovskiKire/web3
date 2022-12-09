package mk.finki.ukim.mk.lab.web.controller;

import mk.finki.ukim.mk.lab.model.Balloon;
import mk.finki.ukim.mk.lab.model.Manufacturer;
import mk.finki.ukim.mk.lab.model.Order;
import mk.finki.ukim.mk.lab.model.ShoppingCart;
import mk.finki.ukim.mk.lab.service.BalloonService;
import mk.finki.ukim.mk.lab.service.ManufacturerService;
import mk.finki.ukim.mk.lab.service.OrderService;
import mk.finki.ukim.mk.lab.service.ShoppingCartService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/balloon")
public class BalloonController {
    public final BalloonService balloonService;
    public final ManufacturerService manufacturerService;
    public final OrderService orderService;
    public final ShoppingCartService shoppingCartService;

    public BalloonController(BalloonService balloonService, ManufacturerService manufacturerService, OrderService orderService, ShoppingCartService shoppingCartService) {
        this.balloonService = balloonService;
        this.manufacturerService = manufacturerService;
        this.orderService = orderService;
        this.shoppingCartService = shoppingCartService;
    }

    @GetMapping
    public String getBalloonsPage(@RequestParam(required = false) String error, Model model) {
        if (error != null && !error.isEmpty()) {
            model.addAttribute("hasError", true);
            model.addAttribute("error", error);
        }
        List<Balloon> balloons = this.balloonService.listAll();
        //List<Manufacturer> manufacturers = this.manufacturerService.findAll();
        model.addAttribute("balloons", balloons);
        //model.addAttribute("manufacturers", manufacturers);
        return "listBalloons";

    }

    @PostMapping("/add")
    public String saveBalloon(@RequestParam String name,
                              @RequestParam String description,
                              @RequestParam Long manufacturer) {

        this.balloonService.save(name, description, manufacturer);
        return "redirect:/balloon";
    }

    @GetMapping("/edit-form/{id}")
    public String editBalloonPage(@PathVariable Long id, Model model) {
        if (this.balloonService.findById(id).isPresent()) {
            Balloon balloon = this.balloonService.findById(id).get();
            //List<Balloon> balloons = this.balloonService.listAll();
            List<Manufacturer> manufacturers = this.manufacturerService.findAll();
            //model.addAttribute("balloons", balloons);
            model.addAttribute("manufacturers", manufacturers);
            model.addAttribute("balloon", balloon);
            return "add-balloon";
        }
        return "redirect:/balloon?error=ProductNotFound";
        //return  "redirect: /balloon";
    }

    @GetMapping("/add-form")
    public String addBalloonPage(Model model) {
        // List<Balloon> balloons = this.balloonService.listAll();
        List<Manufacturer> manufacturers = this.manufacturerService.findAll();
        //model.addAttribute("balloons", balloons);
        model.addAttribute("manufacturers", manufacturers);
        return "add-balloon";
    }

    @DeleteMapping("/delete/{id}")
    public String deleteBalloon(@PathVariable Long id) {
        this.balloonService.deleteById(id);
        return "redirect:/balloon";
    }

    @PostMapping("/order/{id}")
    public String saveOrder(@PathVariable Long id, @RequestParam String dateOrder, HttpServletRequest req){
        Balloon balloon = this.balloonService.findById(id).get();
        String cartId = (String) req.getSession().getAttribute("cartId");
        String balloonColor = balloon.getName();
        String balloonSize = balloon.getDescription();

        Order order = this.orderService.save(balloonColor,  balloonSize, LocalDateTime.parse(dateOrder), Long.valueOf(cartId));
        Optional<ShoppingCart> shoppingCart = this.shoppingCartService.findbyId(Long.parseLong(cartId));
        String user = shoppingCart.get().getUser().getUsername();
        String date = shoppingCart.get().getDateCreated().toString();

        this.shoppingCartService.addBalloonToShoppingCart(user, LocalDateTime.parse(date), order.getOrderId());

        return "redirect:/shopping-cart";
    }
}
