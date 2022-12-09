package mk.finki.ukim.mk.lab.web;

import mk.finki.ukim.mk.lab.service.BalloonService;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring5.SpringTemplateEngine;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;

@WebServlet(name = "BalloonOrderServlet", urlPatterns = "/BalloonOrder.do")
public class BalloonOrderServlet extends HttpServlet {
    public final SpringTemplateEngine springTemplateEngine;
    public final BalloonService balloonService;
    public static String Name;
    public static String Address;
    public static LocalDateTime Date;

    public BalloonOrderServlet(SpringTemplateEngine springTemplateEngine, BalloonService balloonService) {
        this.springTemplateEngine = springTemplateEngine;
        this.balloonService = balloonService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        WebContext context = new WebContext(req,resp,req.getServletContext());
        this.springTemplateEngine.process("deliveryInfo", context, resp.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Name = req.getParameter("clientName");
        Address = req.getParameter("clientAddress");
        Date = LocalDateTime.parse(req.getParameter("OrderDate"));
        req.getSession().setAttribute("Name", Name);
        req.getSession().setAttribute("Address", Address);
        req.getSession().setAttribute("OrderDate", Date);


        resp.sendRedirect("/Confirmation");

    }
}
