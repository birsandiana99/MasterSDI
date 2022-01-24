package clients;

import entities.Dog;
import interfaces.DogsBI;
import interfaces.PeopleBI;

import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/updateDog")
public class UpdateDog extends HttpServlet {
    @EJB
    private DogsBI dogs;
    @EJB
    private PeopleBI people;


    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int dogId = Integer.parseInt(request.getParameter("idD"));
        String name = request.getParameter("name");
        int age = Integer.parseInt(request.getParameter("age"));
        String breed = request.getParameter("breed");
        int idP = Integer.parseInt(request.getParameter("idP"));

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        out.println("<html><head><title>Dog</title></head><body>");
        out.println(dogId + " " + name + " " + age + " " +breed + " "+ idP);

        out.println("</body>");

        dogs.updateDog(new Dog(dogId,name,breed,age, people.getPerson(idP)));
//        response.forward("/client");
    }

    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }


}
