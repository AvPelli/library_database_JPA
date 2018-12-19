/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.ugent.iii.dao;

import be.ugent.iii.entities.*;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceUnit;
import javax.persistence.TypedQuery;

/**
 *
 * @author axeld
 */
public class BibliotheekDao implements AutoCloseable {
    @PersistenceUnit
    EntityManagerFactory emf;
    
    public BibliotheekDao() {
        emf = Persistence.createEntityManagerFactory("projectJpaPU");
    }

    @Override
    public void close() throws Exception {
        emf.close();
    }
    
    // <editor-fold defaultstate="collapsed" desc="algemene methodes om entiteiten toe te voegen">
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
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="methodes om bibliotheken toe te voegen">
    public void addBibliotheek(Bibliotheek bib) {
        addObject(bib);
        if (!bib.getCollecties().isEmpty()) {
            addCollecties(bib.getCollecties());
        }
    }
    
    public void addBibliotheken(List<Bibliotheek> lijst) {
        for (Bibliotheek bib : lijst) {
            addBibliotheek(bib);
        }
    }
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="methodes om collecties toe te voegen">
    public void addCollectie(Collectie collectie) {
        addObject(collectie);
        if (!collectie.getBoeken().isEmpty()) {
            addBoeken(collectie.getBoeken());
        }
    }
    
    public void addCollecties(List<Collectie> collecties) {
        for (Collectie c : collecties) {
            addCollectie(c);
        }
    }
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="methodes om boeken toe te voegen">
    public void addBoek(Boek boek) {
        addObject(boek);
        // DIT GEEFT EEN FOUT!! detached entity...
        /*
        if (!boek.getAuteurs().isEmpty()) {
            addAuteurs(boek.getAuteurs());
        }
        */
    }
    
    public void addBoeken(List<Boek> boeken) {
        for (Boek b : boeken) {
            addBoek(b);
        }
    }
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="methodes om auteurs toe te voegen">
    public void addAuteur(Auteur auteur){
        if (!auteur.getBoeken().isEmpty()) {
            addBoeken(auteur.getBoeken());
        }
        addObject(auteur);
    }
    
    public void addAuteurs(List<Auteur> auteurs){
        for (Auteur auteur : auteurs) {
            addAuteur(auteur);
        }
    }
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="methodes om gegevens van entiteiten uit de database op te halen">
    public List<Bibliotheek> getBibliotheken() {
        EntityManager em = emf.createEntityManager();
        List<Bibliotheek> lijst = em.createQuery("select b from Bibliotheek b", Bibliotheek.class).getResultList();
        em.close();
        return lijst;
    }
    
    public List<Collectie> getCollecties() {
        EntityManager em = emf.createEntityManager();
        List<Collectie> collecties = em.createQuery("select c from Collectie c", Collectie.class).getResultList();
        em.close();
        return collecties;
    }
    
    public List<Boek> getBoeken() {
        EntityManager em = emf.createEntityManager();
        List<Boek> boeken = em.createQuery("select b from Boek b", Boek.class).getResultList();
        em.close();
        return boeken;
    }
    
    public List<Auteur> getAuteurs() {
        EntityManager em = emf.createEntityManager();
        List<Auteur> auteurs = em.createQuery("select a from Auteur a", Auteur.class).getResultList();
        em.close();
        return auteurs;
    }
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="methodes om gegevens van entiteiten uit de database op te halen">
    public Boek getBoek(int id) {
        EntityManager em = emf.createEntityManager();
        Boek boek = em.find(Boek.class, id);
        em.close();
        return boek;
    }
    
    public Bibliotheek zoekBib(int id) {
        EntityManager em = emf.createEntityManager();
        Bibliotheek bib = em.find(Bibliotheek.class, id);
        em.close();
        return bib;
    }
    
    public Bibliotheek getBibliotheek(String naam) {
        EntityManager em = emf.createEntityManager();
        TypedQuery<Bibliotheek> query = em.createQuery("select b from Bibliotheek b where b.naam = ?1", Bibliotheek.class).setParameter(1, naam);
        Bibliotheek bib = query.getResultList().get(0);
        return bib;
    }
    // </editor-fold>
    
}
