/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package G6RentCar.RentCar.View;

import G6RentCar.RentCar.Model.Reservaciones;
import java.util.Date;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author angie
 */
public interface InterfaceReservaciones extends CrudRepository<Reservaciones,Integer> {
    
    public List<Reservaciones> findAllByStatus(String status);
    
    /**
     * Creacion de un query method para generar el intervalo de fechas
     */
    public List<Reservaciones> findAllByStartDateAfterAndStartDateBefore(Date dateOne, Date dateTwo);
    
    /**
     * de los clientes cuentan por cliente, p
     * @return 
     */
    @Query ("SELECT c.client, COUNT(c.client) from Reservaciones AS c group by c.client order by COUNT(c.client)DESC")
    public List<Object[]> countTotalReservationsByClient();
    
    
}
