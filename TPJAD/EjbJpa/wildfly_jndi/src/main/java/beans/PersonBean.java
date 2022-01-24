package beans;

import entities.Dog;
import entities.Person;
import interfaces.PeopleBI;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Stateless
@Remote(PeopleBI.class)
public class PersonBean implements PeopleBI {
    @PersistenceContext(unitName = "jndi")
    private EntityManager entityManager;


    @Override
    public List<Dog> getDogsForPerson(int idPerson) {
        TypedQuery<Dog> query = entityManager.createQuery("select d from Dog d " +
                "where d.person = " + idPerson, Dog.class);
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


    public Person deletePerson(Person pers) {
        Person p = getPerson(pers.getIdPerson());
        entityManager.createQuery("delete from Person pe where pe.idPerson=" + pers.getIdPerson() ).executeUpdate();
        return pers;

    }

    @Override
    public Person updatePerson(Person person) {
        Person p = getPerson(person.getIdPerson());
        if(p != null){
            entityManager.createQuery("update Person pe set pe.name='" + person.getName() + "', pe.gender='" +
                    person.getGender() +  "', pe.age=" + person.getAge() +
                    " where pe.idPerson = " + p.getIdPerson()).executeUpdate();
            return p;
        }
        return null;
    }
}
