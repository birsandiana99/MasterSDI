package view;

import ctrl.CarsController;
import domain.Car;
import repo.Repository;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class dispatcher extends HttpServlet {
    private CarsController ctrl = new CarsController(new Repository());

    private void handleAction(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        System.out.println("AICI: " + action);

        req.setAttribute("ctrl", ctrl);

//        RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/cars");
//        requestDispatcher.forward(req, resp);
        String brand, color, code;
        int year, id;
        if(action!=null && action.equals("update")){
            RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/update");
            requestDispatcher.forward(req, resp);
        }
        else if(action!=null && action.equals("confirmUpdate")){
//            RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/cars");
            id = Integer.parseInt(req.getParameter("id"));
            color = req.getParameter("color");
            System.out.println("ID "+id);
            ctrl.updateCar(id, color);
            resp.sendRedirect("/dispatcher");
            return;
//            requestDispatcher.forward(req, resp);
        }
        else if(action!=null &&  action.equals("add")){
            RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/add");
            requestDispatcher.forward(req, resp);
        }
        else if(action!=null &&  action.equals("confirmAdd")){
//            RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/dispatcher");
            color = req.getParameter("color");
            brand = req.getParameter("brand");
            System.out.println("BRAND::: " +  brand);
            year = Integer.parseInt(req.getParameter("year"));
            code = req.getParameter("code");
            ctrl.addCar(new Car(code, color, brand, year));
            resp.sendRedirect("/dispatcher");
            return;
//            requestDispatcher.forward(req, resp);
        }
        else if(action!=null &&  action.equals("delete")){
            RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/delete");
            requestDispatcher.forward(req, resp);
        }
        else if(action!=null &&  action.equals("confirmDelete")){
            id = Integer.parseInt(req.getParameter("id"));
            ctrl.deleteCar(id);
            resp.sendRedirect("/dispatcher");
            return;
//            RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/cars");
//            requestDispatcher.forward(req, resp);
        }
//        else{
            RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/cars");
            requestDispatcher.forward(req, resp);
//        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        handleAction(req, resp);
//        RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/cars");
//        requestDispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        handleAction(req, resp);
//        RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/cars");
//        requestDispatcher.forward(req, resp);
    }


}
