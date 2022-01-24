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

@WebServlet("/dogs")
public class DogsClient extends HttpServlet {
    @EJB
    private DogsBI dogs;
    @EJB
    private PeopleBI people;


    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int personId = Integer.parseInt(request.getParameter("idP"));

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        out.println("<html><head><title>Dog Care Center</title></head>");

        out.println("<body>");

        out.println("Dogs: ");
        for (Dog d : people.getDogsForPerson(personId)) {
            out.println("<form action=\"updateDog\" method=\"POST\">");
            out.println("<input type='hidden' name='action' value='updateDog'>");
            out.println("ID: <input type=\"text\" readonly name=\"idD\" value=\"" + d.getIdDog() +"\">\n");
            out.println("<td>Name: <input type=\"text\" name=\"name\" value=\"" + d.getName() +"\"></td>\n");
            out.println("<td>Breed: <input type=\"text\" name=\"breed\" value=\"" + d.getBreed() +"\"></td>\n");
            out.println("<td>Age: <input type=\"text\" name=\"age\" value=\"" + d.getAge() +"\"></td>\n");
            out.println("<td>Caretaker: <input readonly type=\"text\" name=\"idP\" value=\"" + d.getPerson().getIdPerson() +"\"></td>\n");
            out.println("<td><input type=\"submit\" name=\"updateBtn\" value=\" Update\"></td>\n");
            out.println("</form");
            out.println("<td> <form action=\"updateDog\" method=\"POST\">");
            out.println("<input type='hidden' name='action' value='deleteDog'>");
            out.println("<input type=\"hidden\" readonly name=\"idD\" value=\"" + d.getIdDog() +"\">");
            out.println("<td><input type=\"submit\" name=\"deleteBtn\" value=\"Delete\"></td>\n");
            out.println("</form </td>");
        }
        out.println("</body></html>");
        out.close();
    }

    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }


}
