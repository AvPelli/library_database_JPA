/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo;

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
 * @ResponseBody = idem als RequestBody maar nu is het antwoord in JSON
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
}
