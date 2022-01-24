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

@WebServlet("/dogs")
public class DogsClient extends HttpServlet {
    @EJB
    private DogsBI dogs;
    @EJB
    private PeopleBI people;


    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int personId = Integer.parseInt(request.getParameter("idP"));
//        int dogId = Integer.parseInt(request.getParameter("idP"));


        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        out.println("<html><head><title>Dog Care Center</title></head>");


        out.println("<body>");

        out.println("Dogs: ");
        for (Dog d : people.getDogsForPerson(personId)) {
            out.println("<form action=\"updateDog\" method=\"POST\">");
            out.println("<input type=\"hidden\" name=\"idD\" value=\"" + d.getIdDog() +"\">\n");
            out.println("<td>Name: <input type=\"text\" name=\"name\" value=\"" + d.getName() +"\"></td>\n");
            out.println("<td>Breed: <input type=\"text\" name=\"breed\" value=\"" + d.getBreed() +"\"></td>\n");
            out.println("<td>Age: <input type=\"text\" name=\"age\" value=\"" + d.getAge() +"\"></td>\n");
            out.println("<td>Caretaker: <input readonly type=\"text\" name=\"idP\" value=\"" + d.getPerson().getIdPerson() +"\"></td>\n");
            out.println("<td><input type=\"submit\" name=\"updateBtn\" value=\" Update\"></td>\n");
            out.println("</form");
        }

        out.println("</body></html>");
        out.close();
    }

    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }


}
