package web.service;

import web.model.Car;

import java.util.ArrayList;
import java.util.List;

public class ServiceCar {

    public List<Car> getCars() {
        List<Car> cars = new ArrayList<>();
        cars.add(new Car("Toyota", "Crown", 120));
        cars.add(new Car("Suzuki", "Vitara", 140));
        cars.add(new Car("Nissan", "Микра", 110));
        return cars;
    }
}
