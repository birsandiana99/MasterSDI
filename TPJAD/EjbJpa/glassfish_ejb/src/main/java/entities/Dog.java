package entities;
import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table(name="dogs")
public class Dog implements Serializable {
    @Id
    @GeneratedValue
    private int idDog;
    private String name;
    private String breed;
    private int age;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="idPerson")
    private Person person;

    public Dog(int idDog, String name, String breed, int age, Person person) {
        this.idDog = idDog;
        this.name = name;
        this.breed = breed;
        this.age = age;
        this.person = person;
    }

    public Dog() {
    }


    public int getIdDog() {
        return idDog;
    }

    public String getName() {
        return name;
    }

    public String getBreed() {
        return breed;
    }

    public int getAge() {
        return age;
    }

    public Person getPerson() {
        return person;
    }

    public void setIdDog(int id) {
        this.idDog = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    @Override
    public String toString() {
        return "Dog{" +
                "id=" + idDog +
                ", name='" + name + '\'' +
                ", breed='" + breed + '\'' +
                ", age=" + age +
                ", person=" + person.getIdPerson() +
                '}';
    }
}
