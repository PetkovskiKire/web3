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

@WebServlet(name="Balloon-Servlet", urlPatterns="")
public class BalloonListSevlet extends HttpServlet {
    public final SpringTemplateEngine springTemplateEngine;
    public final BalloonService balloonService;

    public static String balloonName;

    public BalloonListSevlet(SpringTemplateEngine springTemplateEngine, BalloonService balloonService) {
        this.springTemplateEngine = springTemplateEngine;
        this.balloonService = balloonService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        WebContext context = new WebContext(req,resp,req.getServletContext());
        context.setVariable("listBalloons", this.balloonService.listAll());
        this.springTemplateEngine.process("listBalloons", context, resp.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        balloonName = req.getParameter("color");
        req.getSession().setAttribute("balloonName", balloonName);
        resp.sendRedirect("/SelectBalloon");
    }
}
