/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package G6RentCar.RentCar.Controller;

import G6RentCar.RentCar.Model.Reservaciones;
import G6RentCar.RentCar.Report.ContadorClientes;
import G6RentCar.RentCar.Report.StatusReservas;
import G6RentCar.RentCar.Services.ServiciosReservaciones;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author angie
 */
@RestController
@RequestMapping("/api/Reservation")
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})

public class ControladorReservaciones {

    @Autowired
    private ServiciosReservaciones servicio;

    @GetMapping("/all")
    public List<Reservaciones> getReservations() {
        return servicio.getAll();
    }

    @GetMapping("/{id}")
    public Optional<Reservaciones> getReservation(@PathVariable("id") int reservationId) {
        return servicio.getReservation(reservationId);
    }

    @PostMapping("/save")
    @ResponseStatus(HttpStatus.CREATED)
    public Reservaciones save(@RequestBody Reservaciones reservation) {
        return servicio.save(reservation);
    }

    @PutMapping("/update")
    @ResponseStatus(HttpStatus.CREATED)
    public Reservaciones update(@RequestBody Reservaciones reservation) {
        return servicio.update(reservation);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public boolean delete(@PathVariable("id") int reservationId) {
        return servicio.deleteReservation(reservationId);
    }

    /**
     * Método para obtener todas las reservaciones en una fecha específica
     *
     * @return
     */
    @GetMapping("report-dates/{from}/{to}")
    public List<Reservaciones> getByDate(@PathVariable("from") String sFrom, @PathVariable("to") String sTo){
        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
        Date from = null;
        Date to = null;
        try {
            from = formato.parse(sFrom);
            to = formato.parse(sTo);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return servicio.getByDate(from, to);
    }

    /**
     * Método para obtener el conteo las reservas completas de cada cliente
     * @return
     */
    @GetMapping("report-clients")
    public List<Object> getClientReport(){
        return servicio.getClientReport();
    }

    /**
     * Método para obtener el mapeo para retornar el conteo de reservas completas vs canceladas
     * @return
     */
    @GetMapping("report-status")
    public LinkedHashMap<String, Integer> getVs(){
        return servicio.getVs();
    }
}