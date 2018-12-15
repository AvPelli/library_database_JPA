/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo;

import com.example.demo.entities.Persoon;
import com.example.demo.entities.Werknemer;
import com.example.demo.services.PersoonService;
import com.example.demo.services.WerknemerService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author Arthur
 * 
 * Uitleg: 
 * @RequestBody = annotatie om aan te geven dat de gestuurde data in JSON formaat is
 * @ResponseBody = annotatie om aan te geven dat de ontvangen data in JSON formaat is
 * 
 * 
 * Hoe functionaliteit testen?
 * Installeer "Postman" en doe requests op http://localhost:8080/{mapping}
 * waarbij {mapping} de 'value' is in de code hieronder
 */
@Controller
public class PersonenController {
    
    @Autowired
    private PersoonService persoonService;
    @Autowired
    private WerknemerService werknemerService;
    
    //PERSONEN
    @RequestMapping(value = "/personen", method = RequestMethod.GET)
    @ResponseBody
    public List<Persoon> getAllPersonen(){
        return persoonService.getAllPersonen();
    }
    
    @RequestMapping(value = "/personen/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Persoon getPersoon(@PathVariable int id){
        return persoonService.getPersoon(id);
    }
    
    @RequestMapping(method = RequestMethod.POST, value = "/personen")
    @ResponseBody
    public void addPersoon(@RequestBody Persoon persoon){
        persoonService.addPersoon(persoon);
    }
    
    //WERKNEMERS
    @RequestMapping(method = RequestMethod.GET, value = "/werknemers")
    @ResponseBody
    public List<Werknemer> getAllWerknemers(){
        return werknemerService.getAllPersonen();
    }
    
    @RequestMapping(value = "/werknemers/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Werknemer getWerknemer(@PathVariable int id){
        return werknemerService.getWerknemer(id);
    }
    
    @RequestMapping(method = RequestMethod.POST, value = "/werknemers")
    @ResponseBody
    public void addWerknemer(@RequestBody Werknemer persoon){
        werknemerService.addWerknemer(persoon);
    }

}
