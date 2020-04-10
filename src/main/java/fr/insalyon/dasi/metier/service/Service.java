package fr.insalyon.dasi.metier.service;

import fr.insalyon.dasi.dao.ClientDao;
import fr.insalyon.dasi.dao.EmployeDAO;
import fr.insalyon.dasi.dao.JpaUtil;
import fr.insalyon.dasi.metier.modele.Client;
import fr.insalyon.dasi.metier.modele.Employe;
import fr.insalyon.dasi.metier.modele.ProfilAstrologique;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import fr.insalyon.dasi.util.AstroTest;
import java.io.IOException;

/**
 *
 * @author DASI Team
 */
public class Service {

    protected ClientDao clientDao = new ClientDao();
    protected EmployeDAO empDao = new EmployeDAO();

    public Long inscrireClient(Client client) throws IOException {
        Long resultat = null;
        AstroTest profilAstro = new AstroTest();
 
        JpaUtil.creerContextePersistance();
        try {
            List<String> astroClient = profilAstro.getProfil(client.getPrenom(),client.getNaissance());
            client.getProfilAstro().setSigneZodiaque(astroClient.get(0));
            client.getProfilAstro().setSigneChinois(astroClient.get(1));
            client.getProfilAstro().setCouleur(astroClient.get(2));
            client.getProfilAstro().setAnimal(astroClient.get(3));
            
            JpaUtil.ouvrirTransaction();
            clientDao.creer(client);
            JpaUtil.validerTransaction();
            resultat = client.getId();
        } catch (Exception ex) {
            Logger.getAnonymousLogger().log(Level.WARNING, "Exception lors de l'appel au Service inscrireClient(client)", ex);
            JpaUtil.annulerTransaction();
            resultat = null;
        } finally {
            JpaUtil.fermerContextePersistance();
        }
        return resultat;
    }

    public Client rechercherClientParId(Long id) {
        Client resultat = null;
        JpaUtil.creerContextePersistance();
        try {
            resultat = clientDao.chercherParId(id);
        } catch (Exception ex) {
            Logger.getAnonymousLogger().log(Level.WARNING, "Exception lors de l'appel au Service rechercherClientParId(id)", ex);
            resultat = null;
        } finally {
            JpaUtil.fermerContextePersistance();
        }
        return resultat;
    }

    public Client authentifierClient(String mail, String motDePasse) {
        Client resultat = null;
        JpaUtil.creerContextePersistance();
        try {
            // Recherche du client
            Client client = clientDao.chercherParMail(mail);
            if (client != null) {
                // Vérification du mot de passe
                if (client.getMotDePasse().equals(motDePasse)) {
                    resultat = client;
                }
            }
        } catch (Exception ex) {
            Logger.getAnonymousLogger().log(Level.WARNING, "Exception lors de l'appel au Service authentifierClient(mail,motDePasse)", ex);
            resultat = null;
        } finally {
            JpaUtil.fermerContextePersistance();
        }
        return resultat;
    }

    public List<String> obtenirPredictions(Client client, int[] notes) {
        List<String> resultat = null;
        AstroTest testProfilAstro = new AstroTest();
        
        JpaUtil.creerContextePersistance();
        try {
            String couleur = client.getProfilAstro().getCouleur();
            String animal = client.getProfilAstro().getAnimal();
            
            int sante = notes[0];
            int amour = notes[1];
            int travail = notes[2];
            
            resultat = testProfilAstro.getPredictions(couleur, animal, amour, sante, travail);
            
        } catch (Exception ex) {
            Logger.getAnonymousLogger().log(Level.WARNING, "Exception lors de l'appel au Service obtenirPredictions(Client client, int[] notes)", ex);
            resultat = null;
        } finally {
            JpaUtil.fermerContextePersistance();
        }
        return resultat;
    }
    
    public boolean initEmp() throws IOException {
        boolean flag = false; // 0 si tout ok
        
        JpaUtil.creerContextePersistance();
        try {
            JpaUtil.ouvrirTransaction();
            Employe[] tab = new Employe[15];
            tab[0] = new Employe("Sierra", "Camilo", 'M', "csr@predictif.com", "123456", 0);
            tab[1] = new Employe("Versmee", "Erwan", 'M', "ev@predictif.com", "654321", 0);
            tab[2] = new Employe("Dupont", "François", 'M', "fd@predictif.com", "hjvdfk", 0);
            //empDao.creerEmploye(camilo);
            for (int i=0;i<3;i++)
            {
                empDao.creerEmploye(tab[i]);
            }
            JpaUtil.validerTransaction();

        } catch (Exception ex) {
            Logger.getAnonymousLogger().log(Level.WARNING, "Exception lors de l'appel au Service initEmp()", ex);
            JpaUtil.annulerTransaction();

        } finally {
            JpaUtil.fermerContextePersistance();
        }
        return flag;
    }
    
    
    
    /*public List<Client> listerClients() {
        List<Client> resultat = null;
        JpaUtil.creerContextePersistance();
        try {
            resultat = clientDao.listerClients();
        } catch (Exception ex) {
            Logger.getAnonymousLogger().log(Level.WARNING, "Exception lors de l'appel au Service listerClients()", ex);
            resultat = null;
        } finally {
            JpaUtil.fermerContextePersistance();
        }
        return resultat;
    }*/

}
