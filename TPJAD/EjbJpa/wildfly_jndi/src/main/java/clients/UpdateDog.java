package clients;

import entities.Dog;
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

@WebServlet("/updateDog")
public class UpdateDog extends HttpServlet {
    private final DogsBI dogs;
    private final PeopleBI people;

    public UpdateDog() throws NamingException {
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
        if(action!=null && action.equals("updateDog")){
            int dogId = Integer.parseInt(request.getParameter("idD"));
            String name = request.getParameter("name");
            int age = Integer.parseInt(request.getParameter("age"));
            String breed = request.getParameter("breed");
            int idP = Integer.parseInt(request.getParameter("idP"));
            dogs.updateDog(new Dog(dogId,name,breed,age, people.getPerson(idP)));
        }
        else  if(action!=null && action.equals("deleteDog")){
            int dogId = Integer.parseInt(request.getParameter("idD"));
            dogs.deleteDog(dogs.findDog(dogId));
        }
        RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/client");
        requestDispatcher.forward(request, response);

    }

    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }


}
