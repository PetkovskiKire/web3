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

@WebServlet(name="Select-Balloon-Servlet", urlPatterns = "/SelectBalloon")
public class SelectBalloonServlet extends HttpServlet {

    public final SpringTemplateEngine springTemplateEngine;
    public final BalloonService balloonService;



    public SelectBalloonServlet(SpringTemplateEngine springTemplateEngine, BalloonService balloonService) {
        this.springTemplateEngine = springTemplateEngine;
        this.balloonService = balloonService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        WebContext context = new WebContext(req,resp,req.getServletContext());
        this.springTemplateEngine.process("selectBalloonSize", context, resp.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String balloonName = req.getParameter("color");
        String balloonSize = req.getParameter("size");
        req.getSession().setAttribute("balloonName", balloonName);
        req.getSession().setAttribute("balloonSize", balloonSize);
        resp.sendRedirect("/BalloonOrder.do");
    }
}
