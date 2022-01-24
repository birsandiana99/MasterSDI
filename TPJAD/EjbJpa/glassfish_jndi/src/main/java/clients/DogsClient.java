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

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Properties;

@WebServlet("/dogs")
public class DogsClient extends HttpServlet {
    private final DogsBI dogs;
    private final PeopleBI people;

    public DogsClient() throws NamingException {
        Properties props = new Properties();
        props.put("java.naming.factory.initial", "com.sun.enterprise.naming.impl.SerialInitContextFactory");
        props.put("java.naming.factory.url.pkgs", "com.sun.enterprise.naming");
        props.put("java.naming.factory.state", "com.sun.corba.ee.impl.presentation.rmi.JNDIStateFactoryImpl");
        Context context = new InitialContext(props);
        dogs = (DogsBI) context.lookup("java:global/glassfish_jndi-1.0-SNAPSHOT/DogBean!interfaces.DogsBI");
        people = (PeopleBI) context.lookup("java:global/glassfish_jndi-1.0-SNAPSHOT/PersonBean!interfaces.PeopleBI");

    }

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
