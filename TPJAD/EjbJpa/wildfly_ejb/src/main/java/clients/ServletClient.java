package clients;

import entities.Dog;
import entities.Person;
import interfaces.DogsBI;
import interfaces.PeopleBI;

import javax.ejb.EJB;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
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
//        String personName = request.getParameter("personName");
//        String addPerson = request.getParameter("addPerson");
//        String emplName = request.getParameter("emplName");
//        int age = -1;
//        try { age = Integer.parseInt(request.getParameter("age")); } catch(Exception e) {}
//        String sex = request.getParameter("sex");
//        long id = -1;
//        try { id = Long.parseLong(request.getParameter("id")); } catch(Exception e) {}
//        String createEmpl = request.getParameter("createEmpl");
//        if (addPerson != null && personName != null)
//            facade.createDept(deptName);
//        else if (createEmpl != null && emplName != null && sex != null && id >= 0)
//            facade.createEmpl(id, emplName, age, sex);
        int rows = 5;
        int cols = 14;
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        out.println("<html><head><title>Dog Care Center</title></head>");
//        out.println("<button> See Dogs </button>");

        out.println("<body>");
//        out.println("<form method=\"post\" action=\"client\">" +
//                "<table><input type=\"text\" name=\"personName\" value=\"\"/></td><td>");
//        out.println("<input type=\"submit\" name=\"addPerson\" value=\"Add Person\"/></td></tr><tr>");
//        out.println("<td>Person Name:</td><td><input type=\"text\" name=\"personName\" value=\"\"/>");
//        out.println("</td></tr><tr><td>Gender:</td><td>");
//        out.println("<input type=\"text\" name=\"gender\" value=\"\"/>");
//        out.println("<input type=\"submit\" name=\"addPerson\" value=\"Create Employee\"/></td></tr></table>");

        String s = "Dogs:\n";
        for (Dog d : dogs.getDogs()) {
            s += d.toString()+"\n";
            rows++;
            cols = Math.max(cols, d.toString().length());
        }
        s += "People:\n";
        out.println("<tr>");
        for (Person p : people.getPeople()) {
            out.println("<td>");
//            s += p.toString()+"\n";
//            s += "Dogs: ";
//            List<Dog> dogsForP = people.getDogsForPerson(p.getIdPerson());
//            for(Dog d: dogsForP) {
//                s += d.getName() + " ";
//            }

            out.println("<form action=\"dogs\" method=\"POST\">");
//            out.println("<input type=\"hidden\" name=\"action\" value=\"update\">\n");
            out.println("<input type=\"hidden\" name=\"idP\" value=\"" + p.getIdPerson() +"\">\n");
            out.println("<td><input type=\"text\" name=\"name\" value=\"" + p.getName() +"\"></td>\n");
            out.println("<td><input type=\"text\" name=\"gender\" value=\"" + p.getGender() +"\"></td>\n");
            out.println("<td><input type=\"submit\" name=\"updateBtn\" value=\" See Dogs\"></td>\n");
            out.println("</form");



                    rows++;
            cols = Math.max(cols, p.toString().length() + 1);
        }

        cols += 10;


//        out.println("<tr><td>All objects:</td></tr>");
//        out.println("<tr><td colspan=\"3\"><textarea rows=\""+rows+"\" cols=\""+cols+"\"></textarea></td></tr> ");

        out.println(s+"</table></form></body></html>");
        out.close();
    }

    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }


}
