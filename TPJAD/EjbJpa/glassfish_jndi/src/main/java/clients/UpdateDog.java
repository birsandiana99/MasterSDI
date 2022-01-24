package clients;

import entities.Dog;
import interfaces.DogsBI;
import interfaces.PeopleBI;
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

@WebServlet("/updateDog")
public class UpdateDog extends HttpServlet {
    private final DogsBI dogs;
    private final PeopleBI people;

    public UpdateDog() throws NamingException {
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
