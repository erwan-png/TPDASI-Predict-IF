/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insalyon.dasi.dao;

import fr.insalyon.dasi.metier.modele.Employe;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import static java.util.stream.Collectors.toMap;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

/**
 *
 * @author eversmee
 */
public class EmployeDao {
    
    public void creerEmploye(Employe employe) {
        EntityManager em = JpaUtil.obtenirContextePersistance();
        em.persist(employe);
    }
        
    public Employe chercherParId(Long employeId) {
        EntityManager em = JpaUtil.obtenirContextePersistance();
        return em.find(Employe.class, employeId); // renvoie null si l'identifiant n'existe pas
    }
    
    public Employe chercherParMail(String employeMail) {
        EntityManager em = JpaUtil.obtenirContextePersistance();
        TypedQuery<Employe> query = em.createQuery("SELECT e FROM Employe e WHERE e.mail = :mail", Employe.class);
        query.setParameter("mail", employeMail); // correspond au paramètre ":mail" dans la requête
        List<Employe> employes = query.getResultList();
        Employe result = null;
        if (!employes.isEmpty()) {
            result = employes.get(0); // premier de la liste
        }
        return result;
    }
    
    public List<Employe> listerEmployes() {
        EntityManager em = JpaUtil.obtenirContextePersistance();
        TypedQuery<Employe> query = em.createQuery("SELECT e FROM Employe e ORDER BY e.nom ASC, e.prenom ASC", Employe.class);
        return query.getResultList();
    }
    
    public List<Employe> listerEmployesParPriorite(char genreMedium) {
        EntityManager em = JpaUtil.obtenirContextePersistance();
        TypedQuery<Employe> query = em.createQuery("SELECT e FROM Employe e WHERE e.genre =:genre AND e.disponibilite = 1 ORDER BY e.nom ASC, e.prenom ASC", Employe.class);
        query.setParameter("genre", genreMedium);
        List<Employe> employesOK = query.getResultList();
        
        if(employesOK!=null) {
            
            int nbConsultMini=employesOK.get(0).getNbConsultations();
            
            for (int i=0;i<employesOK.size();i++) {
                if (employesOK.get(i).getNbConsultations()<nbConsultMini) {
                    nbConsultMini=employesOK.get(i).getNbConsultations();
                }
            }
            
            int taille = employesOK.size();
            for (int i=0;i<taille;i++) {
                if (employesOK.get(i).getNbConsultations()!=nbConsultMini) {
                    employesOK.remove(i);
                }
            }
        }
        return employesOK;
    }
        
    public void gererConsultation(Employe employe) {
        EntityManager em = JpaUtil.obtenirContextePersistance();
        em.merge(employe);
    }
    
    public Map<Long,Integer> obtenirStatEmploye() {
        EntityManager em = JpaUtil.obtenirContextePersistance();
        Map<Long,Integer> statEmploye = new HashMap<>();
        
        TypedQuery<Object[]> query = em.createQuery("SELECT e.nbConsultations, e.id  FROM Employe e", Object[].class);
        List<Object[]> l = query.getResultList();
        
        l.forEach((e) -> {
            Integer nbr = (int) e[0];
            Long id = (long) e[1];
            statEmploye.put(id,nbr);
        });
        
        HashMap<Long, Integer> statEmployeSorted = statEmploye.entrySet().stream().sorted(Collections.reverseOrder(Map.Entry.comparingByValue())).collect(toMap(e -> e.getKey(), e -> e.getValue(), (e1, e2) -> e1,LinkedHashMap::new));
        Iterator it = statEmployeSorted.entrySet().iterator();
        System.out.println(statEmployeSorted);
        
        int cmpt = 0; // pour les 5 premiers
        while(it.hasNext()) {
            if(cmpt>5){
                it.remove();
            }else {
                cmpt++;
            }
            it.next();
        }
        if(cmpt>5){
            it.remove();
        }
        System.out.println(statEmployeSorted);
        return statEmployeSorted;
    }
}
