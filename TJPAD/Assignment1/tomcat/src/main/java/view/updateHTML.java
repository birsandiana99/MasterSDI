package view;

import ctrl.CarsController;
import repo.Repository;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class updateHTML extends HttpServlet {
    private CarsController ctrl;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        String brand, color, code;
        int year, id;
        brand = req.getParameter("brand");
        id = Integer.parseInt(req.getParameter("id"));
        color = req.getParameter("color");
        year = Integer.parseInt(req.getParameter("year"));
        code = req.getParameter("code");
        try{
            ctrl = (CarsController) req.getAttribute("ctrl");}
        catch (Exception e) {
            System.out.println("Exceptie la controller");
            ctrl = new CarsController(new Repository());
        }

        PrintWriter out = resp.getWriter();
        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("</head>");
        out.println("<body>");

        out.println("<form method='POST' action='/dispatcher'>");
        out.println("<input type='hidden' name='action' value='confirmUpdate'>");
        out.println("Brand: <input type='text' readonly name='id' value='"+ id + "'>");
        out.println("Brand: <input type='text' readonly name='brand' value=' "+ brand + "'>");
        out.println("Serie sasiu: <input readonly type='text' name='code' value='" + code + "'>");
        out.println("An fabricatie: <input readonly type='text' name='year' value='" + year + "'>");
        out.println("Culoare: <input type='text' name='color' value='" + color + "'>");
        out.println("<input type='submit' name='updateBtn' value='Actualizeaza'>");
        out.println("</form>");



        out.println("</body>");
        out.println("</html>");
        out.close();
    }
}
