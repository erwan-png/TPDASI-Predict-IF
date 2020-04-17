package fr.insalyon.dasi.dao;

import fr.insalyon.dasi.metier.modele.Medium;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import static java.util.Map.Entry.comparingByValue;
import static java.util.stream.Collectors.toMap;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
/**
 *
 * @author eversmee
 */
public class MediumDao {
    
    public void creerMedium(Medium medium) {
        EntityManager em = JpaUtil.obtenirContextePersistance();
        em.persist(medium);
        }
        
    public Medium chercherParId(Long mediumId) {
        EntityManager em = JpaUtil.obtenirContextePersistance();
        return em.find(Medium.class, mediumId); // renvoie null si l'identifiant n'existe pas
    }
    
    public List<Medium> listerMediums() {
        EntityManager em = JpaUtil.obtenirContextePersistance();
        TypedQuery<Medium> query = em.createQuery("SELECT m FROM Medium m ORDER BY m.denomination ASC", Medium.class);
        return query.getResultList();
    }
    
    public Map<Long,Integer> obtenirStatMedium(){
        EntityManager em = JpaUtil.obtenirContextePersistance();
        Map<Long,Integer> statMedium = new HashMap<>();
        
        TypedQuery<Object[]> query = em.createQuery("SELECT count(c.medium.denomination) AS nbConsult, c.medium.denomination  FROM Consultation c GROUP BY c.medium.denomination", Object[].class);
        List<Object[]> l = query.getResultList();
        
        l.forEach((c) -> {
            TypedQuery<Medium> query_id = em.createQuery("SELECT m FROM Medium m WHERE m.denomination =:denomination", Medium.class);
            query_id.setParameter("denomination", c[1]); 
            //Long nbr = (Long)c[0];
            Integer nbr = (int) (long) c[0];
            statMedium.put(query_id.getSingleResult().getId(), nbr);
        });
        System.out.println (statMedium);
        
        HashMap<Long, Integer> collect = statMedium.entrySet().stream().sorted(comparingByValue()).collect(toMap(e -> e.getKey(), e -> e.getValue(), (e1, e2) -> e2,LinkedHashMap::new));

        
        return statMedium;
    } 
        
}
