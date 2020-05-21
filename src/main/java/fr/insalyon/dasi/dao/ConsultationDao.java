package fr.insalyon.dasi.dao;

import fr.insalyon.dasi.metier.modele.Client;
import fr.insalyon.dasi.metier.modele.Consultation;
import fr.insalyon.dasi.metier.modele.Employe;
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
    
    public Long trouverConsultationEnCours(Employe employe) {
        EntityManager em = JpaUtil.obtenirContextePersistance();
        Long id = null;
        TypedQuery<Consultation> query = em.createQuery("SELECT c FROM Consultation c WHERE (c.heureDebut IS null OR c.heureFin IS null) AND c.employe =:employe", Consultation.class);
        query.setParameter("employe", employe);
        List<Consultation> result = query.getResultList();
        /*result.forEach((consult) ->{
            if(consult.getEmploye()!=employe){
                result.remove(consult);
            }
        });*/
        if(!result.isEmpty()) {
           id = result.get(0).getId_consultation();
        }
        return id;
    }
    
    public List<Consultation> trouverConsultationSansCommentaire(Employe employe) {
        EntityManager em = JpaUtil.obtenirContextePersistance();
        TypedQuery<Consultation> query = em.createQuery("SELECT c FROM Consultation c WHERE c.commentaire IS null AND c.employe =:employe", Consultation.class);
        query.setParameter("employe", employe);
        return query.getResultList();
    }
}
