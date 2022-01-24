package beans;

import entities.Dog;
import entities.Person;
import interfaces.DogsBI;
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
    @PersistenceContext(unitName = "ejb")
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
        this.getPerson(pers.getIdPerson());
        this.entityManager.createQuery("delete from Person pe where pe.idPerson=" + pers.getIdPerson()).executeUpdate();
        return pers;
    }

    public Person updatePerson(Person person) {
        Person p = this.getPerson(person.getIdPerson());
        if (p != null) {
            EntityManager var10000 = this.entityManager;
            String var10001 = person.getName();
            var10000.createQuery("update Person pe set pe.name='" + var10001 + "', pe.gender='" + person.getGender() + "', pe.age=" + person.getAge() + " where pe.idPerson = " + p.getIdPerson()).executeUpdate();
            return p;
        } else {
            return null;
        }
    }
}
