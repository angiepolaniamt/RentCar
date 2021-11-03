/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package G6RentCar.RentCar.Services;

import G6RentCar.RentCar.Model.Cliente;
import G6RentCar.RentCar.Model.Reservaciones;
import G6RentCar.RentCar.Report.ContadorClientes;
import G6RentCar.RentCar.Report.StatusReservas;
import G6RentCar.RentCar.Repository.RepositorioCliente;
import G6RentCar.RentCar.Repository.RepositorioReservaciones;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author angie
 */
@Service
public class ServiciosReservaciones {
    @Autowired
    private RepositorioReservaciones metodosCrud;
    @Autowired
    private RepositorioCliente cliente;
    public List<Reservaciones> getAll(){
        return metodosCrud.getAll();
    }

    public Optional<Reservaciones> getReservation(int reservationId) {
        return metodosCrud.getReservation(reservationId);
    }

    public Reservaciones save(Reservaciones reservation){
        if(reservation.getIdReservation()==null){
            return metodosCrud.save(reservation);
        }else{
            Optional<Reservaciones> e= metodosCrud.getReservation(reservation.getIdReservation());
            if(e.isEmpty()){
                return metodosCrud.save(reservation);
            }else{
                return reservation;
            }
        }
    }

    public Reservaciones update(Reservaciones reservation){
        if(reservation.getIdReservation()!=null){
            Optional<Reservaciones> e= metodosCrud.getReservation(reservation.getIdReservation());
            if(!e.isEmpty()){

                if(reservation.getStartDate()!=null){
                    e.get().setStartDate(reservation.getStartDate());
                }
                if(reservation.getDevolutionDate()!=null){
                    e.get().setDevolutionDate(reservation.getDevolutionDate());
                }
                if(reservation.getStatus()!=null){
                    e.get().setStatus(reservation.getStatus());
                }
                metodosCrud.save(e.get());
                return e.get();
            }else{
                return reservation;
            }
        }else{
            return reservation;
        }
    }

    public boolean deleteReservation(int reservationId) {
        Boolean aBoolean = getReservation(reservationId).map(reservation -> {
            metodosCrud.delete(reservation);
            return true;
        }).orElse(false);
        return aBoolean;
    }
    
    /**
     * Método para buscar reservaciones en una selección por fechas
     * @param from
     * @param until
     * @return
     */
    public ArrayList<Reservaciones> getByDate(Date from, Date until) {

        List<Reservaciones> reservations = metodosCrud.getAll();
        ArrayList<Reservaciones> dateReservation = new ArrayList<>();
        int count = 0;

        for (Reservaciones reservation : reservations) {
            if(from.compareTo(until) < 0) {
                if (reservation.getDevolutionDate().compareTo(from) > 0 && reservation.getStartDate().compareTo(until) < 0) {
                    if ((reservation.getStartDate().compareTo(from) <= 0 || reservation.getStartDate().compareTo(from) >= 0) &&
                            reservation.getDevolutionDate().compareTo(until) <= 0 || reservation.getDevolutionDate().compareTo(until) >= 0) {
                        count++;
                        dateReservation.add(reservation);

                        System.out.println(dateReservation.size());
                    }
                }
            }
        }

        return dateReservation;
    }

    /**
     * Método para obtener la cantidad de reservas completadas y canceladas
     * @return
     */
    public LinkedHashMap<String, Integer> getVs(){
        List<Reservaciones> reservations = metodosCrud.getAll();
        LinkedHashMap<String, Integer> status = new LinkedHashMap<>();
        int completed = 0;
        int cancelled = 0;
        for (Reservaciones reservation : reservations){
            if ("completed".equals(reservation.getStatus().toLowerCase())){
                completed++;
            }else if ("cancelled".equals(reservation.getStatus().toLowerCase())){
                cancelled++;
            }
        }
        status.put("completed", completed);
        status.put("cancelled", cancelled);
        return  status;
    }

    /**
     * Método para obtener la cantidad de reservas completadas de un cliente
     * @return
     */
    public List<Object> getClientReport(){
        List<Cliente> clients = cliente.getAll();
        LinkedHashMap<String,Object> reportClient = new LinkedHashMap<>();
        List<Object> countClients = new ArrayList<>();
        for (Cliente client: clients){
            Integer total = 0;
            for (Reservaciones reservation : client.getReservations()) {
                    total++;
            }
            reportClient.put("total", total);
            reportClient.put("client",client);
            countClients.add(reportClient.clone());
            System.out.println(countClients);
            System.out.println(reportClient);
        }
        return countClients;
    }
}
