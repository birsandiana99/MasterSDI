package entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import javax.persistence.*;

@Entity
@Table(name="people")
public class Person implements Serializable {
    @Id
    @GeneratedValue
    private int idPerson;
    private String name;
    private String gender;
    private int age;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "person")
    Collection<Dog> dogs = new ArrayList<>();

    public Person(int idPerson, String name, String gender, int age) {
        this.idPerson = idPerson;
        this.name = name;
        this.gender = gender;
        this.age = age;
    }

    public Person() {
    }


    public int getIdPerson() {
        return idPerson;
    }

    public void setIdPerson(int idPerson) {
        this.idPerson = idPerson;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

//    public Collection<Dog> getDogs() {
//        return dogs;
//    }
    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
    public void setDogs(Collection<Dog> dogs) {
        this.dogs = dogs;
    }

    @Override
    public String toString() {
        return "Person{" +
                "idPerson=" + idPerson +
                ", name='" + name + '\'' +
                ", gender='" + gender + '\'' +
//                ", dogs=" + dogs +
                '}';
    }
}
