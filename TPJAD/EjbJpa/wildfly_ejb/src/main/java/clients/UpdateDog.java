package clients;

import entities.Dog;
import entities.Person;
import interfaces.DogsBI;
import interfaces.PeopleBI;

import javax.ejb.EJB;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

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
