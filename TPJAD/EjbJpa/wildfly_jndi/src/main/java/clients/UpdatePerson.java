package clients;

import entities.Dog;
import entities.Person;
import interfaces.DogsBI;
import interfaces.PeopleBI;

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

@WebServlet("/updatePerson")
public class UpdatePerson extends HttpServlet{
    private final DogsBI dogs;
    private final PeopleBI people;

    public UpdatePerson() throws NamingException {
        Properties JNDIProps = new Properties();
        JNDIProps.put(Context.INITIAL_CONTEXT_FACTORY, "org.jboss.naming.remote.client.InitialContextFactory");
        JNDIProps.put(Context.PROVIDER_URL, "http-remoting://localhost:8080");
        Context context = new InitialContext(JNDIProps);
        dogs = (DogsBI) context.lookup("wildfly_jndi-1.0-SNAPSHOT/DogBean!interfaces.DogsBI");
        people = (PeopleBI) context.lookup("wildfly_jndi-1.0-SNAPSHOT/PersonBean!interfaces.PeopleBI");
    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");
        if(action!=null && action.equals("updatePerson")){
            int personID = Integer.parseInt(request.getParameter("idP"));
            String name = request.getParameter("PName");
            int age = Integer.parseInt(request.getParameter("PAge"));
            String gender = request.getParameter("PGender");
            people.updatePerson(new Person(personID,name,gender, age));
        }
        else  if(action!=null && action.equals("deletePerson")){
            int personID = Integer.parseInt(request.getParameter("idP"));
            people.deletePerson(people.getPerson(personID));
        }
        RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/client");
        requestDispatcher.forward(request, response);

    }

    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }



}