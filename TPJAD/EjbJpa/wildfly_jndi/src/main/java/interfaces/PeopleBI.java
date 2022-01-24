package interfaces;

import entities.Dog;
import entities.Person;

import java.util.List;

public interface PeopleBI {
    List<Dog> getDogsForPerson(int idPerson);
    List<Person> getPeople();
    Person getPerson(int idPerson);
    Person deletePerson(Person person);
    Person updatePerson(Person person);
}
