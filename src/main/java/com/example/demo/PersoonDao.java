/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo;

import com.example.demo.Persoon;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Arthur
 */

public class PersoonDao implements AutoCloseable{
    
    private EntityManagerFactory emf;
    
    public PersoonDao(){
        emf = Persistence.createEntityManagerFactory("PersoonPU");
    }
    
    @Override
    public void close() throws Exception {
        emf.close();
    }
    
    private void addObject(Object o) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(o);
        em.getTransaction().commit();
        em.close();
    }

    private void addObjecten(Collection collection) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        for (Object o : collection) {
            em.persist(o);
        }
        em.getTransaction().commit();
        em.close();
    }
    
    public void addPersoon(Persoon p){
        addObject(p);
    }
    
    public void addPersonen(List<Persoon> list){
        addObjecten(list);
    }
    
}
