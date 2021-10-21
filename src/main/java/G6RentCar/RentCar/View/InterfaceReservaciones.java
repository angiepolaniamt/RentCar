/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package G6RentCar.RentCar.View;

import G6RentCar.RentCar.Model.Reservaciones;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author angie
 */
public interface InterfaceReservaciones extends CrudRepository<Reservaciones,Integer> {
    
}
