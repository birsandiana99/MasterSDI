package beans;

import entities.Dog;
import interfaces.DogsBI;


import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
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

    @Override
    public Dog deleteDog(Dog dog) {
        Dog d = findDog(dog.getIdDog());
        entityManager.createQuery("delete from Dog dg where dg.idDog=" + dog.getIdDog() ).executeUpdate();
        return d;

    }
}
