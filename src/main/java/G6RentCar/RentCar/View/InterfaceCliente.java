/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package G6RentCar.RentCar.View;

import G6RentCar.RentCar.Model.Cliente;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author angie
 */
public interface InterfaceCliente extends CrudRepository<Cliente,Integer> {
    
}
