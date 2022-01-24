package interfaces;

import entities.Dog;

import java.util.List;

public interface DogsBI {
    Dog addDog(Dog dog);
    List<Dog> getDogs();

    Dog findDog(int index);

    Dog updateDog(Dog dog);

    Dog deleteDog(Dog dog);
}
