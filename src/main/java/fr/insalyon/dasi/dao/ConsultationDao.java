package fr.insalyon.dasi.dao;

import fr.insalyon.dasi.metier.modele.Consultation;
import java.util.List;
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
}
