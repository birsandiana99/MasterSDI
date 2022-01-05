package repo;
import domain.Car;

import java.util.ArrayList;
import java.util.List;
public class Repository {
    private List<Car> carList;

    public Repository() {
        this.carList = new ArrayList<>();
    }

    public void add(Car c){
        this.carList.add(c);
        c.setId(carList.indexOf(c));
    }

    public List<Car> getAll(){
        return this.carList;
    }

    public void updateCar(int id, String color){
        getCarById(id).setColor(color);
    }

    public void deleteCar(Car c){
        carList.remove(c);
        for(Car car: carList){
            car.setId(carList.indexOf(car));
        }
    }

    public Car getCarById(int id){
        for (Car c:carList){
            if(c.getId() == id){
                return c;
            }
        }
        return null;
    }

}
