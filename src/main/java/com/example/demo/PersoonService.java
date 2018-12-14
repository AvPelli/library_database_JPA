/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Arthur
 */
@Service
public class PersoonService {
    
    @Autowired
    private PersoonRepository persoonRepository;
    
    public List<Persoon> getAllPersonen(){
        List<Persoon> personen = new ArrayList<>();
        
        //personen::add : zie "method reference"
        persoonRepository.findAll().forEach(personen::add);
        return personen;
    }
    
    public Persoon getPersoon(int id){
        return persoonRepository.findById(id).get();
    }
    
    public void addPersoon(Persoon p){
        persoonRepository.save(p);
    }
}
