/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package G6RentCar.RentCar.Repository;

import G6RentCar.RentCar.Model.Gama;
import G6RentCar.RentCar.View.InterfaceGama;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author angie
 */
@Repository
public class RepositorioGama {
    @Autowired
    private InterfaceGama crud;
    public List<Gama> getAll(){
        return (List<Gama>) crud.findAll();
    }
    public Optional<Gama> getGama(int id){
        return crud.findById(id);
    }

    public Gama save(Gama gama){
        return crud.save(gama);
    }
    public void delete(Gama gama){
       crud.delete(gama);
    }
    
}
