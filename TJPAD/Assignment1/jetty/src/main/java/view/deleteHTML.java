package view;

import ctrl.CarsController;
import domain.Car;
import repo.Repository;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class deleteHTML extends HttpServlet {
    private CarsController ctrl;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        try{
            ctrl = (CarsController) req.getAttribute("ctrl");
        System.out.println("aaaa" + ctrl);}
        catch (Exception e) {
            System.out.println("Exceptie la controller");
            ctrl = new CarsController(new Repository());
        }
        String brand, color, code;
        int year, id;
//        brand = req.getParameter("brand");
        id = Integer.parseInt(req.getParameter("id"));
        System.out.println("ctrl " + ctrl);
        Car c = ctrl!=null ? ctrl.getCarById(id) : new Car("","","",0);
        brand = c.getBrand();
        color = c.getColor();
        year = c.getYear();
        code = c.getCode();
//        color = req.getParameter("color");
//        year = Integer.parseInt(req.getParameter("year"));
//        code = req.getParameter("code");


        PrintWriter out = resp.getWriter();
        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("</head>");
        out.println("<body>");
        out.println("<form method='GET' action='/add'>");
        out.println("<input id='add' type='submit' value='Adauga'>");
        out.println("</form>");
        out.println("<form method='POST' action='/dispatcher'>");
        out.println("<input type='hidden' name='action' value='confirmDelete'>");
        out.println("<input type='hidden' name='id' value='" + id + "'>");
        out.println("Brand: <input readonly type='text' name='brand' value='" + brand + "'>");
        out.println("Cod sasiu: <input readonly type='text' name='code' value='" + code +"'>");
        out.println("An fabricatie: <input readonly type='text' name='year' value='" + year + "'>");
        out.println("Culoare: <input readonly type='text' name='color' value='" +color + "'>");
        out.println("<input type='submit' name='deleteBtn' value='Sterge'>");
        out.println("</form>");

        out.println("</body>");
        out.println("</html>");
        out.close();
    }
}
