package ctrl;

import domain.Car;
import repo.Repository;

import java.util.List;

public class CarsController {
    private Repository repo;


    public CarsController(Repository repo){
        this.repo = repo;
        init();
    }
    private void init() {
        Car c1 = new Car( "2adgs","blue","skoda", 1999);
        Car c2 = new Car( "5sdsd","white","dacia", 2010);
        Car c3 = new Car( "8fdfd","black","porsche", 1997);
        Car c4 = new Car( "0dfgf","black","bmw", 2020);

        repo.add(c1);
        repo.add(c2);
        repo.add(c3);
        repo.add(c4);
    }

    public List<Car> getAll(){
        return this.repo.getAll();
    }

    public void updateCar(int id, String color){
        repo.updateCar(id, color);
    }

    public void deleteCar(int id){
        System.out.println(repo.getAll());
        System.out.println("LA DELETE "+ repo.getAll().get(id));
        repo.deleteCar(getCarById(id));
    }
    public Car getCarById(int id){
        return repo.getCarById(id);
    }

    public void addCar(Car c){
        repo.add(c);
    }
}
