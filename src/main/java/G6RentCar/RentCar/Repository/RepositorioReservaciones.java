/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package G6RentCar.RentCar.Repository;

import G6RentCar.RentCar.Model.Cliente;
import G6RentCar.RentCar.Model.Reservaciones;
import G6RentCar.RentCar.Report.ContadorClientes;
import G6RentCar.RentCar.View.InterfaceReservaciones;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author angie
 */
@Repository
public class RepositorioReservaciones {
     @Autowired
    private InterfaceReservaciones crud4;

    public List<Reservaciones> getAll(){
        return (List<Reservaciones>) crud4.findAll();
    }
    public Optional<Reservaciones> getReservation(int id){
        return crud4.findById(id);
    }
    public Reservaciones save(Reservaciones reservation){
        return crud4.save(reservation);
    }
    public void delete(Reservaciones reservation){
        crud4.delete(reservation);
    }
    /**
     * Filtramos la reservacion y le pasamos el status de la reservacion
     * @param status
     * @return 
     */
    public List<Reservaciones> ReservacionesStatus(String status){
        return crud4.findAllByStatus(status);
    }
    
    public List<Reservaciones> ReservacionTiempo (Date a, Date b){
        return crud4.findAllByStartDateAfterAndStartDateBefore(a, b);
    }
    
    public List<ContadorClientes> getTopClientes(){
        List<ContadorClientes> res=new  ArrayList<>();
        List<Object[]>report = crud4.countTotalReservationsByClient();
        for(int i=0; i<report.size();i++){
            res.add(new ContadorClientes((Long)report.get(i)[1],(Cliente)report.get(i)[0]));
        }
        return res;
    }
}
