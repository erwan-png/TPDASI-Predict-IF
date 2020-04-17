package fr.insalyon.dasi.metier.service;

import fr.insalyon.dasi.dao.ClientDao;
import fr.insalyon.dasi.dao.EmployeDao;
import fr.insalyon.dasi.dao.ConsultationDao;
import fr.insalyon.dasi.dao.JpaUtil;
import fr.insalyon.dasi.dao.MediumDao;
import fr.insalyon.dasi.metier.modele.Astrologue;
import fr.insalyon.dasi.metier.modele.Cartomancien;
import fr.insalyon.dasi.metier.modele.Client;
import fr.insalyon.dasi.metier.modele.Employe;
import fr.insalyon.dasi.metier.modele.Consultation;
import fr.insalyon.dasi.metier.modele.Medium;
import fr.insalyon.dasi.metier.modele.Spirite;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import fr.insalyon.dasi.util.AstroTest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
/**
 *
 * @author DASI Team
 */
public class Service {

    protected ClientDao clientDao = new ClientDao();
    protected EmployeDao employeDao = new EmployeDao();
    protected ConsultationDao consultationDao = new ConsultationDao();
    protected MediumDao mediumDao = new MediumDao();


    public Long inscrireClient(Client client) throws IOException {
        Long resultat = null;
        AstroTest profilAstro = new AstroTest();
        ServiceOutils serviceOutils = new ServiceOutils();
        
        List<String> astroClient = profilAstro.getProfil(client.getPrenom(),client.getNaissance());
        client.getProfilAstro().setSigneZodiaque(astroClient.get(0));
        client.getProfilAstro().setSigneChinois(astroClient.get(1));
        client.getProfilAstro().setCouleur(astroClient.get(2));
        client.getProfilAstro().setAnimal(astroClient.get(3));
 
        JpaUtil.creerContextePersistance();
        try {
            JpaUtil.ouvrirTransaction();
             Client client_existant = clientDao.chercherParMail(client.getMail());
             if(client_existant == null) {
                 clientDao.creerClient(client);
                 resultat = client.getId();
             }
            JpaUtil.validerTransaction();
        } catch (Exception ex) {
            Logger.getAnonymousLogger().log(Level.WARNING, "Exception lors de l'appel au Service inscrireClient(client)", ex);
            JpaUtil.annulerTransaction();
            resultat = null;
        } finally {
            JpaUtil.fermerContextePersistance();
        }
        if(resultat!=null) {
            serviceOutils.genererMailClientSuccesInscription(client);
        } else {
            serviceOutils.genererMailClientEchecInscription(client);
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
    
    public Employe rechercherEmployeParId(Long id) {
        Employe resultat = null;
        JpaUtil.creerContextePersistance();
        try {
            resultat = employeDao.chercherParId(id);
        } catch (Exception ex) {
            Logger.getAnonymousLogger().log(Level.WARNING, "Exception lors de l'appel au Service rechercherEmployeParId(id)", ex);
            resultat = null;
        } finally {
            JpaUtil.fermerContextePersistance();
        }
        return resultat;
    }
    
    public Medium rechercherMediumParId(Long id) {
        Medium resultat = null;
        JpaUtil.creerContextePersistance();
        try {
            resultat = mediumDao.chercherParId(id);
        } catch (Exception ex) {
            Logger.getAnonymousLogger().log(Level.WARNING, "Exception lors de l'appel au Service rechercherMediumParId(id)", ex);
            resultat = null;
        } finally {
            JpaUtil.fermerContextePersistance();
        }
        return resultat;
    }
    
    public Consultation rechercherConsultationParId(Long id) {
        Consultation resultat = null;
        JpaUtil.creerContextePersistance();
        try {
            resultat = consultationDao.chercherParId(id);
        } catch (Exception ex) {
            Logger.getAnonymousLogger().log(Level.WARNING, "Exception lors de l'appel au Service rechercherConsultationParId(id)", ex);
            resultat = null;
        } finally {
            JpaUtil.fermerContextePersistance();
        }
        return resultat;
    }
    
    public Client trouverClient(String mail) {
        Client resultat = null;
        JpaUtil.creerContextePersistance();
        try {
            // Recherche du client
            resultat = clientDao.chercherParMail(mail);
        } catch (Exception ex) {
            Logger.getAnonymousLogger().log(Level.WARNING, "Exception lors de l'appel au Service trouverClient(mail)", ex);
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
    
    public Employe authentifierEmploye(String mail, String motDePasse) {
        Employe resultat = null;
        JpaUtil.creerContextePersistance();
        try {
            // Recherche du client
            Employe employe = employeDao.chercherParMail(mail);
            if (employe != null) {
                // Vérification du mot de passe
                if (employe.getMotDePasse().equals(motDePasse)) {
                    resultat = employe;
                }
            }
        } catch (Exception ex) {
            Logger.getAnonymousLogger().log(Level.WARNING, "Exception lors de l'appel au Service authentifierEmploye(mail,motDePasse)", ex);
            resultat = null;
        } finally {
            JpaUtil.fermerContextePersistance();
        }
        return resultat;
    }

    public List<String> obtenirPredictions(Client client, int[] notes) {
        List<String> resultat = null;
        AstroTest testProfilAstro = new AstroTest();
        
        String couleur = client.getProfilAstro().getCouleur();
        String animal = client.getProfilAstro().getAnimal();
        
        int sante = notes[0];
        int amour = notes[1];
        int travail = notes[2];
        
        JpaUtil.creerContextePersistance();
        
        try {
            
            resultat = testProfilAstro.getPredictions(couleur, animal, amour, sante, travail);
            
        } catch (IOException ex) {
            Logger.getAnonymousLogger().log(Level.WARNING, "Exception lors de l'appel au Service obtenirPredictions(Client client, int[] notes)", ex);
            resultat = null;
        } finally {
            JpaUtil.fermerContextePersistance();
        }
        return resultat;
    }
    

    public boolean initialiserEmploye() throws IOException {
        boolean flag = false; // 0 si tout ok
        
        JpaUtil.creerContextePersistance();
        try {
            JpaUtil.ouvrirTransaction();
            Employe[] tab = new Employe[15];
            tab[0] = new Employe("Sierra", "Camilo", 'H', "csr@predictif.com", "123456","00 11 22 33 44 55", 0);
            tab[1] = new Employe("Versmee", "Erwan", 'H', "ev@predictif.com", "654321","00 11 22 33 44 56", 0);
            tab[2] = new Employe("Crouzet", "Nina", 'F', "nc@predictif.com", "nina","00 11 22 33 44 60", 0);
            tab[3] = new Employe("Ragot", "Andres", 'H', "ar@predictif.com", "andres","00 11 22 33 44 58", 0);
            tab[4] = new Employe("Cohen", "Carlos", 'H', "cc@predictif.com", "carlos","00 11 22 33 44 59", 0);
            tab[5] = new Employe("Dupont", "Camille", 'F', "cd@predictif.com", "camille","00 11 22 33 44 57", 0);
            tab[6] = new Employe("Orange", "Clementine", 'F', "oc@predictif.com", "clementine","00 11 22 33 44 61", 0);
            tab[7] = new Employe("Ajami", "Wissam", 'H', "wa@predictif.com", "wissam","00 11 22 33 44 62", 0);
            tab[8] = new Employe("Velasquez", "Sebastian", 'H', "sv@predictif.com", "sebastian","00 11 22 33 44 63", 0);
            tab[9] = new Employe("Eyraud", "Jim", 'H', "je@predictif.com", "jim","00 11 22 33 44 64", 0);
            tab[10] = new Employe("Frerot", "Baptise", 'H', "bf@predictif.com", "baptiste","00 11 22 33 44 64", 0);
            tab[11] = new Employe("Dultheo", "Christopher", 'H', "cd@predictif.com", "christopher","00 11 22 33 44 65", 0);
            tab[12] = new Employe("Gineste", "Matteo", 'H', "mg@predictif.com", "matteo","00 11 22 33 44 66", 0);
            tab[13] = new Employe("Johnson", "Ann", 'F', "aj@predictif.com", "ann","00 11 22 33 44 67", 0);
            tab[14] = new Employe("Kessaria", "Douraya", 'F', "dk@predictif.com", "douraya","00 11 22 33 44 68", 0);
            //empDao.creerEmploye(camilo);
            for (int i=0;i<15;i++)
            {
                employeDao.creerEmploye(tab[i]);
            }
            JpaUtil.validerTransaction();
            
            flag = true;

        } catch (Exception ex) {
            Logger.getAnonymousLogger().log(Level.WARNING, "Exception lors de l'appel au Service initialiserEmploye()", ex);
            JpaUtil.annulerTransaction();
            flag = false;
        } finally {
            JpaUtil.fermerContextePersistance();
        }
        return flag;
    }
    
    public Long demanderMedium(Client client, Medium medium){
        Long id = null;
        ServiceOutils serviceOutils = new ServiceOutils();
        
        List<Employe> employesOK = null;
        JpaUtil.creerContextePersistance();
        
        try {
            employesOK = employeDao.listerEmployesParPriorite(medium.getGenre());
        } catch (Exception ex) {
            Logger.getAnonymousLogger().log(Level.WARNING, "Exception lors de l'appel au Service demanderMedium(medium)", ex);
            id = null;
        } finally {
            JpaUtil.fermerContextePersistance();
        }
        
        if(employesOK != null) {
                id = employesOK.get(0).getId();
                serviceOutils.genererNotificationClient(client,medium,employesOK.get(0));
        }
        
        return id;
    }
    
    public Long commencerConsultation(Consultation consultation) throws IOException {
        Long id;
        consultation.getEmploye().setDisponibilite(false);
        consultation.getEmploye().setNbConsultations(consultation.getEmploye().getNbConsultations()+1);
        
        JpaUtil.creerContextePersistance();
        try {
            JpaUtil.ouvrirTransaction();
            consultationDao.creerConsultation(consultation);
            employeDao.gererConsultation(consultation.getEmploye());
            JpaUtil.validerTransaction();
            id = consultation.getId_consultation();
            
        } catch (Exception ex) {
            Logger.getAnonymousLogger().log(Level.WARNING, "Exception lors de l'appel au Service commencerConsultation(consultation)", ex);
            JpaUtil.annulerTransaction();
            consultation.getEmploye().setDisponibilite(true);
            id = null;
        } finally {
            JpaUtil.fermerContextePersistance();
        }
        return id;
    }
    
    public Long terminerConsultation(Consultation consultation, Date heureFin ) throws IOException {
        Long id;
        consultation.getEmploye().setDisponibilite(true);
        consultation.setHeureFin(heureFin);
        
        JpaUtil.creerContextePersistance();
        
        try {
            JpaUtil.ouvrirTransaction();
            consultationDao.modifierConsultation(consultation);
            employeDao.gererConsultation(consultation.getEmploye());
            JpaUtil.validerTransaction();
            id = consultation.getId_consultation();
            
        } catch (Exception ex) {
            Logger.getAnonymousLogger().log(Level.WARNING, "Exception lors de l'appel au Service terminerConsultation(consultation, heureFin)", ex);
            JpaUtil.annulerTransaction();
            consultation.getEmploye().setDisponibilite(false);
            id = null;
        } finally {
            JpaUtil.fermerContextePersistance();
        }
        return id;
    }
    
    public Long laisserCommentaire(Consultation consultation, String commentaire) throws IOException {
        Long id;
        consultation.setCommentaire(commentaire);
        
        JpaUtil.creerContextePersistance();
        
        try {
            JpaUtil.ouvrirTransaction();
            consultationDao.modifierConsultation(consultation);
            JpaUtil.validerTransaction();
            id = consultation.getId_consultation();
            
        } catch (Exception ex) {
            Logger.getAnonymousLogger().log(Level.WARNING, "Exception lors de l'appel au Service laisserCommentaire(consultation, commentaire)", ex);
            JpaUtil.annulerTransaction();
            consultation.getEmploye().setDisponibilite(false);
            id = null;
        } finally {
            JpaUtil.fermerContextePersistance();
        }
        return id;
    }
    
    public boolean initialiserMediums() throws IOException {
        boolean success = false;
        
        JpaUtil.creerContextePersistance();
        
        List<String> supportUn = new ArrayList<>();
        List<String> supportDeux = new ArrayList<>();
        List<String> supportTrois = new ArrayList<>();
        supportUn.add("Boule de cristal");
        supportDeux.add("Marc de café");
        supportDeux.add("Boule de cristal");
        supportDeux.add("Oreilles de lapin ");
        supportTrois.add("Langue de renard");
            
        Spirite spiriteUn = new Spirite(supportUn, "Gwenaëlle",'F', "Spécialiste des grandes conversations au-delà de TOUTES les frontières.");
        Spirite spiriteDeux = new Spirite(supportDeux, "Professeur Tran",'H', "Votre avenir est devant vous : regardons-le ensemble !");
        Spirite spiriteTrois = new Spirite(supportTrois, "Jean Philippe",'H', "Oui, j'utilise une langue de renard");
        Spirite spiriteQuatre = new Spirite(supportUn, "Platon",'H', "Ce que je sais, c’est que je ne sais rien sur ton future oups");
        Spirite spiriteCinq = new Spirite(supportUn, "William",'H', "Je ne sais plus quoi dire");
            
        Cartomancien cartomancienUn = new Cartomancien("Mme Irma",'F', "Comprenez votre entourage grâce à mes cartes ! Résultats rapides.");
        Cartomancien cartomancienDeux = new Cartomancien("Endora",'F', "Mes cartes répondront à toutes vos questions personnelles.");
        Cartomancien cartomancienTrois = new Cartomancien("Gambit",'H', "Ma maitrisse des cartes dépasse les limites du compréhensible");
        Cartomancien cartomancienQuatre = new Cartomancien("Cards",'H', "Bim Bam Boum Mes Cartes disent Bonjour");
        Cartomancien cartomancienCinq = new Cartomancien("Ygritte",'H', "Si jamais les cartes disent ue tu mourra je ne te dirai pas ;)");
        Cartomancien cartomancienSix = new Cartomancien("Victor Hugo",'H', "Le Bonheur est parfois caché sous mes cartes");
        Cartomancien cartomancienSept = new Cartomancien("Descartes",'H', "Je connais ton futur, donc je suis");
            
        Astrologue astrologueUn = new Astrologue("École Normale Supérieure d’Astrologie (ENS-Astro)" , 2006,"Serena", 'F',"Basée à Champigny-sur-Marne, Serena vous révèlera votre avenir pour éclairer votre passé." );
        Astrologue astrologueDeux = new Astrologue("Institut des Nouveaux Savoirs Astrologiques" , 2010,"Mr M", 'H',"Avenir, avenir, que nous réserves-tu ? N'attendez plus, demandez à me consulter!" );
        Astrologue astrologueTrois = new Astrologue("Harvard" , 2010,"Rajesh", 'H',"Les étoiles me parlent de toi tous les nuits!" );
        Astrologue astrologueQuatre = new Astrologue("Grece" , -325,"Aristote", 'H',"L'ignorant affirme, le savant doute, le sage m'appelle" );
        Astrologue astrologueCinq = new Astrologue("France" , 1905,"Sartre", 'H',"Dans la vie on ne fait pas ce que l'on veut mais ce que les étoiles disent" );
        Astrologue astrologueSix = new Astrologue("Oui" , 1905,"Einstein", 'H',"La vie est comme une Byciclète, et moi je m'y connais" );
        
        try {
            
            JpaUtil.ouvrirTransaction();
            mediumDao.creerMedium(spiriteUn);
            mediumDao.creerMedium(spiriteDeux);
            mediumDao.creerMedium(spiriteTrois);
            mediumDao.creerMedium(spiriteQuatre);
            mediumDao.creerMedium(spiriteCinq);
            mediumDao.creerMedium(cartomancienUn);
            mediumDao.creerMedium(cartomancienDeux);
            mediumDao.creerMedium(cartomancienTrois);
            mediumDao.creerMedium(cartomancienQuatre);
            mediumDao.creerMedium(cartomancienCinq);
            mediumDao.creerMedium(cartomancienSix);
            mediumDao.creerMedium(cartomancienSept);
            mediumDao.creerMedium(astrologueUn);
            mediumDao.creerMedium(astrologueDeux);
            mediumDao.creerMedium(astrologueTrois);
            mediumDao.creerMedium(astrologueQuatre);
            mediumDao.creerMedium(astrologueCinq);
            mediumDao.creerMedium(astrologueSix);
            JpaUtil.validerTransaction();
            
            success = true;
            
        } catch (Exception ex) {
            Logger.getAnonymousLogger().log(Level.WARNING, "Exception lors de l'appel au Service initialiserMediums()", ex);
            JpaUtil.annulerTransaction();
            
            success = false;
            
        } finally {
            JpaUtil.fermerContextePersistance();
        }
        return success;
    }
    
    public List<Client> listerClients() {
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
    }
    
    public List<Medium> listerMediums() {
        List<Medium> resultat = null;
        JpaUtil.creerContextePersistance();
        try {
            resultat = mediumDao.listerMediums();
        } catch (Exception ex) {
            Logger.getAnonymousLogger().log(Level.WARNING, "Exception lors de l'appel au Service listerMediums()", ex);
            resultat = null;
        } finally {
            JpaUtil.fermerContextePersistance();
        }
        return resultat;
    }
    
    public List<Employe> listerEmployes() {
        List<Employe> resultat = null;
        JpaUtil.creerContextePersistance();
        try {
            resultat = employeDao.listerEmployes();
        } catch (Exception ex) {
            Logger.getAnonymousLogger().log(Level.WARNING, "Exception lors de l'appel au Service listerEmployes()", ex);
            resultat = null;
        } finally {
            JpaUtil.fermerContextePersistance();
        }
        return resultat;
    }
    
    public List<Consultation> consulterHistoriqueConsultationClient(Client client) {
        List<Consultation> resultat = null;
        JpaUtil.creerContextePersistance();
        
        try {
            resultat = consultationDao.obtenirHistoriqueConsultationsClient(client);
        } catch (Exception ex) {
            Logger.getAnonymousLogger().log(Level.WARNING, "Exception lors de l'appel au Service consulterHistoriqueConsultationClient(Client client)", ex);
            resultat = null;
        } finally {
            JpaUtil.fermerContextePersistance();
        }
        return resultat;
    }
    
    public Map<Long,Integer> obtenirStatistique() {
        Map<Long,Integer> statMedium = new HashMap<>();
        
        JpaUtil.creerContextePersistance();
        try {
            statMedium = consultationDao.obtenirStatMedium();
        } catch (Exception ex) {
            Logger.getAnonymousLogger().log(Level.WARNING, "Exception lors de l'appel au Service obtenirStatistique()", ex);
            statMedium = null;
        } finally {
            JpaUtil.fermerContextePersistance();
        }
        return statMedium;
    }
}
