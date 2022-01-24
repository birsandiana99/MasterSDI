package clients;

import entities.Dog;
import entities.Person;
import interfaces.DogsBI;
import interfaces.PeopleBI;

import javax.ejb.EJB;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Properties;

@WebServlet("/client")
public class ServletClient extends HttpServlet {
    private final DogsBI dogs;
    private final PeopleBI people;

    public ServletClient() throws NamingException {
        Properties JNDIProps = new Properties();
        JNDIProps.put(Context.INITIAL_CONTEXT_FACTORY, "org.jboss.naming.remote.client.InitialContextFactory");
        JNDIProps.put(Context.PROVIDER_URL, "http-remoting://localhost:8080");
        Context context = new InitialContext(JNDIProps);
        dogs = (DogsBI) context.lookup("wildfly_jndi-1.0-SNAPSHOT/DogBean!interfaces.DogsBI");
        people = (PeopleBI) context.lookup("wildfly_jndi-1.0-SNAPSHOT/PersonBean!interfaces.PeopleBI");
    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        out.println("<html><head><title>Dog Care Center</title></head>");

        out.println("<body>");

        out.println("Here is the list of employees: \n");
        for (Person p : people.getPeople()) {
            out.println("<tr>");
            out.println("<form method='POST' action='updatePerson'>");
            out.println("<input type='hidden' name='action' value='updatePerson'>");
            out.println("<input type='hidden' name='idP' value=" + p.getIdPerson() + ">");
            out.println("<td> <input type=\"text\" name=\"PName\" value=\"" + p.getName() +"\"></td>\n");
            out.println("<td><input type=\"text\" name=\"PGender\" value=\"" + p.getGender() +"\"></td>\n");
            out.println("<td><input type=\"text\" name=\"PAge\" value=\"" + p.getAge() +"\"></td>\n");
            out.println("<td> <input type='submit' value='Update'></td>");
            out.println("</form>");
            out.println("<td><form method='POST' action='updatePerson'>");
            out.println("<input type='hidden' name='action' value='deletePerson'>");
            out.println("<input type='hidden' name='idP' value=" + p.getIdPerson() + ">");
            out.println("<td> <input type='submit' value='Delete'> </td>");
            out.println("</form></td>");
            out.println("<td>");
            out.println("<form action=\"dogs\" method=\"POST\">");
            out.println("<br/>");
            out.println("<input type=\"hidden\" name=\"idP\" value=\"" + p.getIdPerson() +"\">\n");
            out.println("<td><input type=\"submit\" name=\"dogsBtn\" value=\" See Dogs\"></td></form>\n");
            out.println("</tr>");
        }
        out.println("</table></form></body></html>");
        out.close();
    }

    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }


}
