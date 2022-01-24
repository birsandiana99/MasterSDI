package beans;

import entities.Dog;
import interfaces.DogsBI;
import jakarta.ejb.Remote;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;

import java.util.List;


@Stateless
@Remote(DogsBI.class)
public class DogBean implements DogsBI {
    @PersistenceContext(unitName = "jndi")
    EntityManager entityManager;

    @Override
    public Dog addDog(Dog dog) {
        entityManager.persist(dog);
        return dog;
    }

    @Override
    public List<Dog> getDogs() {
        TypedQuery<Dog> query = entityManager.createQuery("SELECT d from Dog d", Dog.class);
        return query.getResultList();
    }

    @Override
    public Dog findDog(int index) {
        return entityManager.find(Dog.class, index);
    }

    @Override
    public Dog updateDog(Dog dog) {
        Dog d = findDog(dog.getIdDog());
        if(d != null){
            entityManager.createQuery("update Dog dg set dg.name='" + dog.getName() + "', dg.breed='" +
              dog.getBreed() +  "', dg.age=" + dog.getAge() +
                     " where dg.idDog = " + dog.getIdDog()).executeUpdate();
            return d;
        }
        return null;
    }
}
