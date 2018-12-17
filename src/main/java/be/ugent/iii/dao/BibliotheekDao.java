/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.ugent.iii.dao;

import be.ugent.iii.entities.Bibliotheek;
import be.ugent.iii.entities.Boek;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author axeld
 */
public class BibliotheekDao implements AutoCloseable {
    EntityManagerFactory emf;
    
    public BibliotheekDao() {
        emf = Persistence.createEntityManagerFactory("projectJpaPU");
    }

    @Override
    public void close() throws Exception {
        emf.close();
    }
    
    private void addObject(Object object) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(object);
        em.getTransaction().commit();
        em.close();
    }
    
    private void addObjects(Collection collection) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        for (Object object : collection) {
            em.persist(object);
        }
        em.getTransaction().commit();
        em.close();
    }
    
    public void addBoek(Boek boek) {
        addObject(boek);
    }
    
    public void addBoeken(List<Boek> boeken) {
        addObjects(boeken);
    }
    
    public List<Boek> getBoeken() {
        EntityManager em = emf.createEntityManager();
        List<Boek> boeken = em.createQuery("select b from Boek b", Boek.class).getResultList();
        em.close();
        return boeken;
    }
    
    public Boek getBoek(int id) {
        EntityManager em = emf.createEntityManager();
        Boek boek = em.find(Boek.class, id);
        em.close();
        return boek;
    }
    
    public void addBibliotheek(Bibliotheek bib) {
        addObject(bib);
    }
    
    public List<Bibliotheek> getBibliotheken() {
        EntityManager em = emf.createEntityManager();
        List<Bibliotheek> lijst = em.createQuery("select b from Bibliotheek b", Bibliotheek.class).getResultList();
        em.close();
        return lijst;
    }
}
