package beans;

import entities.Dog;
import entities.Person;
import interfaces.PeopleBI;
import jakarta.ejb.Remote;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;

import java.util.List;

@Stateless
@Remote(PeopleBI.class)
public class PersonBean implements PeopleBI {
    @PersistenceContext(unitName = "jndi")
    private EntityManager entityManager;


    @Override
    public List<Dog> getDogsForPerson(int idPerson) {
        TypedQuery<Dog> query = entityManager.createQuery("select d from Dog d " +
                "where d.person.idPerson=" + idPerson, Dog.class);
        return query.getResultList();
    }

    @Override
    public List<Person> getPeople() {
        TypedQuery<Person> query = entityManager.createQuery("select p from Person p", Person.class);
        return query.getResultList();
    }

    @Override
    public Person getPerson(int idPerson) {
        return entityManager.find(Person.class, idPerson);
    }
}
