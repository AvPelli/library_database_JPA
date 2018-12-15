/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo.repositories;

import com.example.demo.entities.Werknemer;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author Arthur
 */
public interface WerknemerRepository extends CrudRepository<Werknemer, Integer>{
    
}
