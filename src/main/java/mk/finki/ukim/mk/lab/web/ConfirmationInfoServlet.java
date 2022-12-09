package mk.finki.ukim.mk.lab.web;

import mk.finki.ukim.mk.lab.service.BalloonService;
import mk.finki.ukim.mk.lab.service.OrderService;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring5.SpringTemplateEngine;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name="ConfirmationInfoServlet", urlPatterns = "/Confirmation")
public class ConfirmationInfoServlet extends HttpServlet {

    public final SpringTemplateEngine springTemplateEngine;
    public final BalloonService balloonService;
    private final OrderService orderService;


    public ConfirmationInfoServlet(SpringTemplateEngine springTemplateEngine, BalloonService balloonService, OrderService orderService) {
        this.springTemplateEngine = springTemplateEngine;
        this.balloonService = balloonService;
        this.orderService = orderService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String clientName = (String) req.getSession().getAttribute("Name");
        String clientAddress = (String) req.getSession().getAttribute("Address");
        String balloonName = (String) req.getSession().getAttribute("balloonName");
        String balloonSize = (String) req.getSession().getAttribute("balloonSize");
        //this.orderService.placeOrder(balloonName, balloonSize);

        WebContext context = new WebContext(req,resp,req.getServletContext());
        context.setVariable("ipAddress",req.getRemoteAddr());
        context.setVariable("clientAgent",req.getHeader("User-Agent"));

        this.springTemplateEngine.process("confirmationInfo", context, resp.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.getSession().invalidate();
        resp.sendRedirect("/balloon");
    }
}
