/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.ugent.iii.dao;

import be.ugent.iii.entiteiten.*;
import java.util.*;
import javax.persistence.*;

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
    public void close() {
        emf.close();
    }

    // <editor-fold defaultstate="collapsed" desc="methodes om entiteiten toe te voegen">
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

    public void addBibliotheek(Bibliotheek bibliotheek) {
        addObject(bibliotheek);
    }

    public void addBibliotheken(List<Bibliotheek> bibliotheeken) {
        addObjects(bibliotheeken);
    }

    public void addCollectie(Collectie collectie) {
        addObject(collectie);
    }

    public void addBoeken(List<Boek> boeken) {
        addObjects(boeken);
    }
    
    public void addLid(Lid lid){
        addObject(lid);
    }
    // </editor-fold>

    public List<Boek> zoekBoekenOpTitel(String titel) {
        EntityManager em = emf.createEntityManager();
        TypedQuery<Boek> query
                = em.createQuery("select b from Boek b where b.titel = ?1", Boek.class).setParameter(1, titel);
        List<Boek> resultaat = query.getResultList();
        em.close();
        return resultaat;
    }

    public List<Boek> zoekBoekenOpTaal(String taal) {
        EntityManager em = emf.createEntityManager();
        TypedQuery<Boek> query
                = em.createQuery("select b from Boek b where b.taal = ?1", Boek.class).setParameter(1, taal);
        List<Boek> resultaat = query.getResultList();
        em.close();
        return resultaat;
    }

    // nog niet getest vanaf hier
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

    public List<Lid> getLeden() {
        EntityManager em = emf.createEntityManager();
        List<Lid> leden = em.createQuery("select l from Lid l", Lid.class).getResultList();
        em.close();
        return leden;
    }

    public List<Lening> getLeningen() {
        EntityManager em = emf.createEntityManager();
        List<Lening> leningen = em.createQuery("select l from Lening l", Lening.class).getResultList();
        em.close();
        return leningen;
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="methodes om entiteiten op te zoeken via hun parameters">
    public Boek getBoek(int id) {
        EntityManager em = emf.createEntityManager();
        Boek boek = em.find(Boek.class, id);
        em.close();
        return boek;
    }

    public List<Boek> getBoekenByTaal(String taal) {
        EntityManager em = emf.createEntityManager();

        Query opdracht = em.createQuery("Select b from Boek b where b.taal = :taal");
        opdracht.setParameter("taal", taal);

        List<Boek> boeken = opdracht.getResultList();
        em.close();
        return boeken;
    }

    public List<Integer> getIdOfBoekenByAuteur(String voornaam, String achternaam) {
        EntityManager em = emf.createEntityManager();

        Query opdracht = em.createQuery("select a from Auteur a where a.achterNaam= :achternaam and a.voorNaam = :voornaam");
        opdracht.setParameter("achternaam", achternaam);
        opdracht.setParameter("voornaam", voornaam);
        List<Auteur> auteurs = opdracht.getResultList();

        List<Integer> result = new ArrayList<>();
        if (!auteurs.isEmpty()) {
            Auteur a = auteurs.get(0);
            int id = a.getId();
            
            /*
                createNativeQuery gebruiken: createQuery() is JPQL, deze kan enkel queries
                opstellen voor ENTITIES. "Auteurs_per_boek" is geen entity maar een joined table.
            */
            Query zoekBoeken = em.createNativeQuery("SELECT BOEK FROM AUTEURS_PER_BOEK WHERE AUTEUR = ?1");
            zoekBoeken.setParameter(1, ""+id);

            result = zoekBoeken.getResultList();
        }
        em.close();
        
        //Checken of er wel degelijk boeken gevonden zijn
        if(result.isEmpty()){
            return null;
        } else {
            return result;
        }
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
        em.close();
        return bib;
    }

    public Lid getLid(String voornaam, String achternaam) {
        EntityManager em = emf.createEntityManager();
        TypedQuery<Lid> query = em.createQuery("select l from Lid l where l.voorNaam = ?1 and l.achterNaam = ?2", Lid.class);
        query.setParameter(1, voornaam);
        query.setParameter(2, achternaam);

        List<Lid> result = query.getResultList();
        if (!result.isEmpty()) {
            Lid lid = result.get(0);
            return lid;
        }
        em.close();
        return null;
    }
    
    public Lid getLid(int id){
        EntityManager em = emf.createEntityManager();
        Lid lid = em.find(Lid.class, id);
        em.close();
        return lid;   
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="methodes om gegevens van entiteiten uit de database te verwijderen">
    public void BoekTeruggebracht(Lid l, Boek b) {
        EntityManager em = emf.createEntityManager();
        Query opdracht = em.createQuery("Select l from Lening l where l.boek.ID = :id and l.lid.ID = :lidid");
        opdracht.setParameter("id", b.getId());
        opdracht.setParameter("lidid", l.getId());

        List<Lening> result = opdracht.getResultList();
        //verwijderen teruggebrachte boek
        em.getTransaction().begin();
        if (!result.isEmpty()) {
            for (Lening le : result) {
                em.remove(le);
            }
        }
        em.getTransaction().commit();
        em.close();
    }

    public boolean VerwijderBoek(int id) {
        //Elk boek zit in een collectie
        EntityManager em = emf.createEntityManager();
        Query opdracht = em.createQuery("select b from Boek b where b.id = :id");
        opdracht.setParameter("id", id);

        List<Boek> result = opdracht.getResultList();
        if (!result.isEmpty()) {
            em.getTransaction().begin();
            for (Boek b : result) {
                em.remove(b);
            }
            em.getTransaction().commit();
            em.close();
            return true;
        }

        em.close();
        return false;
    }

    public void VerwijderLid(String voornaam, String achternaam) {
        EntityManager em = emf.createEntityManager();
        Query query = em.createQuery("select l from Lid l where l.voorNaam = :voornaam and l.achterNaam = :achternaam");
        query.setParameter("voornaam", voornaam);
        query.setParameter("achternaam", achternaam);

        List<Lid> result = query.getResultList();
        em.getTransaction().begin();
        for (Lid lid : result) {
            em.remove(lid);
        }
        em.getTransaction().commit();
        em.close();
    }
    //</editor-fold>

}
