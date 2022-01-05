package ctrl;

import domain.Car;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ServletDispatcher extends HttpServlet{
    public void doGet(HttpServletRequest req,   HttpServletResponse res)
            throws ServletException, IOException {
        String action = req.getParameter("action");
        String carId = req.getParameter("id");

        System.out.println("action" + action);
        System.out.println("id " + carId);

        if(action!=null && action.equals("update")){
            res.sendRedirect(req.getContextPath() + "/update.jsp?carId=" + carId);
        }

        if(action!=null && action.equals("delete")){
            res.sendRedirect(req.getContextPath() + "/delete.jsp?carId=" + carId);
        }
    }
    public void doPost(HttpServletRequest req,   HttpServletResponse res)
            throws ServletException, IOException {
        String action = req.getParameter("action");

        System.out.println("action" + action);

        int carId = (action!=null && !action.equals("confirmAdd")) ? Integer.parseInt(req.getParameter("carId")) : -1;
        String color = req.getParameter("color");
        String code = req.getParameter("code");
        String brand = req.getParameter("brand");
        int year = Integer.parseInt(req.getParameter("year"));

        if(action!=null && action.equals("confirmUpdate")){
            System.out.println("confirm update");


            System.out.println("color  " + color);
            CarsController ctrl = (CarsController) req.getSession().getAttribute("ctrl");
            ctrl.updateCar(carId,color);
            res.sendRedirect(req.getContextPath() + "/index.jsp");
        }
        if(action!=null && action.equals("confirmDelete")){
            System.out.println("confirm delete");

            CarsController ctrl = (CarsController) req.getSession().getAttribute("ctrl");
            ctrl.deleteCar(carId);
            res.sendRedirect(req.getContextPath() + "/index.jsp");
        }

        if(action!=null && action.equals("confirmAdd")){
            System.out.println("confirm add");
            Car car = new Car(code,color,brand,year);
            CarsController ctrl = (CarsController) req.getSession().getAttribute("ctrl");
            ctrl.addCar(car);
            res.sendRedirect(req.getContextPath() + "/index.jsp");
        }
    }
}
