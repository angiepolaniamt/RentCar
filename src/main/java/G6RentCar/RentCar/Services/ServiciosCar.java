/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package G6RentCar.RentCar.Services;

import G6RentCar.RentCar.Model.Car;
import G6RentCar.RentCar.Repository.RepositorioCar;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author angie
 */
@Service
public class ServiciosCar {
         @Autowired
    private RepositorioCar metodosCrud;

    public List<Car> getAll(){
        return metodosCrud.getAll();
    }

    public Optional<Car> getCar(int carId) {
        return metodosCrud.getCar(carId);
    }

    public Car save(Car car){
        if(car.getIdCar()==null){
            return metodosCrud.save(car);
        }else{
            Optional<Car> e=metodosCrud.getCar(car.getIdCar());
            if(e.isEmpty()){
                return metodosCrud.save(car);
            }else{
                return car;
            }
        }
    }

    public Car update(Car car){
        if(car.getIdCar()!=null){
            Optional<Car> e=metodosCrud.getCar(car.getIdCar());
            if(!e.isEmpty()){
                if(car.getName()!=null){
                    e.get().setName(car.getName());
                }
                if(car.getBrand()!=null){
                    e.get().setBrand(car.getBrand());
                }
                if(car.getYear()!=null){
                    e.get().setYear(car.getYear());
                }
                if(car.getDescription()!=null){
                    e.get().setDescription(car.getDescription());
                }
                if(car.getGama()!=null){
                    e.get().setGama(car.getGama());
                }
                metodosCrud.save(e.get());
                return e.get();
            }else{
                return car;
            }
        }else{
            return car;
        }
    }


    public boolean deleteCar(int carId) {
        Boolean aBoolean = getCar(carId).map(car -> {
            metodosCrud.delete(car);
            return true;
        }).orElse(false);
        return aBoolean;
    }
    
}
