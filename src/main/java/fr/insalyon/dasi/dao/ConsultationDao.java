package fr.insalyon.dasi.dao;

import fr.insalyon.dasi.metier.modele.Client;
import fr.insalyon.dasi.metier.modele.Consultation;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

/**
 *
 * @author eversmee
 */
public class ConsultationDao {
    
    public void creerConsultation(Consultation consultation) {
        EntityManager em = JpaUtil.obtenirContextePersistance();
        em.persist(consultation);
    }
    
    public void modifierConsultation(Consultation consultation) {
        EntityManager em = JpaUtil.obtenirContextePersistance();
        em.merge(consultation);
    }
    
    public Consultation chercherParId(Long consultationId) {
        EntityManager em = JpaUtil.obtenirContextePersistance();
        return em.find(Consultation.class, consultationId); // renvoie null si l'identifiant n'existe pas
    }
    
    public List<Consultation> obtenirHistoriqueConsultationsClient(Client client) {
        EntityManager em = JpaUtil.obtenirContextePersistance();
        TypedQuery<Consultation> query = em.createQuery("SELECT c FROM Consultation c WHERE c.client =:client ORDER BY c.dateConsultation DESC", Consultation.class);
        query.setParameter("client", client); 
        return query.getResultList();
    }
    
    public Map<Long,Integer> obtenirStatMedium(){
        EntityManager em = JpaUtil.obtenirContextePersistance();
        TypedQuery<Consultation> query = em.createQuery("SELECT count(c.medium) AS nbConsult, c.medium  FROM Consultation c GROUP BY c.medium.denomination", Consultation.class);
        Map<Long,Integer> statMedium = new HashMap<>();
        query.getResultList().forEach((c) -> {
            System.out.println("Object" + c);
            //statMedium.put(c.getMedium().getId(), query.getFirstResult());
        });
        return statMedium;
    } 
}
