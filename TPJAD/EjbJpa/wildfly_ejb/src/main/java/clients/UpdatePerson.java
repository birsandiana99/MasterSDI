package clients;

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
import java.util.Properties;

@WebServlet("/updatePerson")
public class UpdatePerson extends HttpServlet{
    @EJB
    private DogsBI dogs;
    @EJB
    private PeopleBI people;


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