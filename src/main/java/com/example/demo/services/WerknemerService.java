/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo.services;

import com.example.demo.entities.Werknemer;
import com.example.demo.repositories.WerknemerRepository;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Arthur
 */
@Service
public class WerknemerService {
    
    @Autowired
    private WerknemerRepository werknemerRepository;
    
    public List<Werknemer> getAllPersonen(){
        List<Werknemer> werknemers = new ArrayList<>();
        
        werknemerRepository.findAll().forEach(werknemers::add);
        return werknemers;
    }
    
    public Werknemer getWerknemer(int id){
        return werknemerRepository.findById(id).get();
    }
    
    public void addWerknemer(Werknemer p){
        werknemerRepository.save(p);
    }
      
}
