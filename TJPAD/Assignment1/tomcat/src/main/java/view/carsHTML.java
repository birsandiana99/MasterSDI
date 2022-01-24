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

public class carsHTML extends HttpServlet {
    private CarsController ctrl;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        try{
        ctrl = (CarsController) req.getAttribute("ctrl");
        }
        catch (Exception e) {
            System.out.println("Exceptie la controller");
            ctrl = new CarsController(new Repository());
        }
        System.out.println("CTRL aici  "+  ctrl);
        PrintWriter out = resp.getWriter();
        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<title> Tomcat</title>");
        out.println("</head>");
        out.println("<body>");
        out.println("<div> CARS </div> <br/>");
        out.println("<form method='GET' action='/add'>");
        out.println("<input id='add' type='submit' value='Adauga'>");
        out.println("</form>");
        if(ctrl.getAll().size() > 0){
        for(Car c: ctrl.getAll()){
            out.println("<form method='GET' action='/dispatcher'>");
            out.println("<input type='hidden' name='action' value='update'>");
            out.println("<input type='hidden' name='id' value=" + c.getId() + ">");
            out.println("Brand: <input readonly type='text' name='brand' value=" + c.getBrand() + ">");
            out.println("Serie sasiu: <input readonly type='text' name='code' value=" + c.getCode() + ">");
            out.println("An fabricatie: <input readonly type='text' name='year' value=" + c.getYear() + ">");
            out.println("Culoare: <input readonly type='text' name='color' value=" + c.getColor() + ">");
            out.println("<input type='submit' name='updateBtn' value='Actualizeaza'>");
            out.println("</form>");

            out.println("<form method='GET' action='/dispatcher'>");
            out.println("<input type='hidden' name='action' value='delete'>");
            out.println("<input type='hidden' name='id' value=" + c.getId() + ">");
            out.println("<input type='submit' name='deleteBtn' value='Sterge'>");
            out.println("</form>");
        }}

        out.println("</body>");
        out.println("</html>");
        out.close();
    }
}
